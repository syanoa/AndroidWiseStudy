package com.wayne.algorithm.ktxs

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun Activity.isAppInstalled(packageName: String): Boolean {
    val pm = packageManager
    try {
        pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
        return true
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    return false
}

inline fun ComponentActivity.launchAndRepeatOnLifecycle(
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline block: suspend CoroutineScope.() -> Unit
) {
    lifecycleScope.launch {
        repeatOnLifecycle(minActiveState) {
            block()
        }
    }
}

fun <T : Activity> Activity.toNextPage(nextActivity: Class<T>, extras: Intent.() -> Unit = {}) {
    val intent = Intent(this, nextActivity).apply {
        extras()
//        putExtra(KEY_PAGE_TAG, pageTag)
    }
    startActivity(intent)
}

//fun <T : Activity> BaseActivity.toNextPageForResult(
//    nextActivity: Class<T>,
//    onResult: (Int, Intent?) -> Unit,
//    extras: Intent.() -> Unit = {}
//) {
//    val intent = Intent(this, nextActivity).apply {
//        extras()
//        putExtra(KEY_PAGE_TAG, pageTag)
//    }
//    onActivityResult = onResult
//    resultLauncher.launch(intent)
//}

inline fun <reified T : Activity> Activity.toNextPage(noinline extras: Intent.() -> Unit = {}) {
    toNextPage(T::class.java, extras)
}

//inline fun <reified T : Activity> BaseActivity.toNextPageForResult(
//    noinline extras: Intent.() -> Unit = {},
//    noinline onResult: (Int, Intent?) -> Unit
//) {
//    toNextPageForResult(T::class.java, onResult, extras)
//}



fun Activity.configSystemBar(config: SystemBarConfig.() -> Unit) {
    val (
        decorFitsSystemWindows,
        statusBarColor,
        navigationBarColor,
        statusBarBlackFont,
        navigationBarBlackIcon,
        gestureNavigationTransparent,
        navigationTransparent,
        applyStatusBarView,
        applyNavigationBarView
    ) = SystemBarConfig().apply(config)
    WindowCompat.setDecorFitsSystemWindows(window, decorFitsSystemWindows)
    window.statusBarColor = statusBarColor
    WindowCompat.getInsetsController(window, window.decorView)?.run {
        isAppearanceLightStatusBars = statusBarBlackFont
        isAppearanceLightNavigationBars = navigationBarBlackIcon
    }

    window.decorView.doOnApplyWindowInsets { insets, _, _ ->
        val navigationWindowInsets = insets.getInsets(WindowInsetsCompat.Type.navigationBars())
        val isGestureNavigation = isGestureNavigation(navigationWindowInsets)
        if (!isGestureNavigation) {
            val newNavigationWindowInsets = if (navigationTransparent) {
                window.navigationBarColor = Color.TRANSPARENT
                WindowInsetsCompat.Builder()
                    .setInsets(
                        WindowInsetsCompat.Type.navigationBars(), Insets.of(
                            navigationWindowInsets.left, navigationWindowInsets.top,
                            navigationWindowInsets.right, 0
                        )
                    ).build()
            } else {
                window.navigationBarColor = navigationBarColor
                insets
            }
            ViewCompat.onApplyWindowInsets(window.decorView, newNavigationWindowInsets)
        } else if (isGestureNavigation) {
            val newNavigationWindowInsets = if (gestureNavigationTransparent) {
                window.navigationBarColor = Color.TRANSPARENT
                WindowInsetsCompat.Builder()
                    .setInsets(
                        WindowInsetsCompat.Type.navigationBars(), Insets.of(
                            navigationWindowInsets.left, navigationWindowInsets.top,
                            navigationWindowInsets.right, 0
                        )
                    ).build()
            } else {
                window.navigationBarColor = navigationBarColor
                insets
            }
            ViewCompat.onApplyWindowInsets(window.decorView, newNavigationWindowInsets)
        }
    }
    if (applyStatusBarView != null && applyStatusBarView == applyNavigationBarView) {
        applyStatusBarView.doOnApplyWindowInsets { insets, _, _ ->
            val statusWindowInsets = insets.getInsets(WindowInsetsCompat.Type.statusBars())
            val navigationWindowInsets = insets.getInsets(WindowInsetsCompat.Type.navigationBars())
            applyStatusBarView.updatePadding(top = statusWindowInsets.top, bottom = navigationWindowInsets.bottom)
        }
    } else {
        applyStatusBarView?.doOnApplyWindowInsets { insets, _, _ ->
            val statusWindowInsets = insets.getInsets(WindowInsetsCompat.Type.statusBars())
            applyStatusBarView.updatePadding(top = statusWindowInsets.top)
        }
        applyNavigationBarView?.doOnApplyWindowInsets { insets, _, _ ->
            val navigationWindowInsets = insets.getInsets(WindowInsetsCompat.Type.navigationBars())
            applyNavigationBarView.updatePadding(bottom = navigationWindowInsets.bottom)
        }
    }
}

data class InitialPadding(
    val left: Int,
    val top: Int,
    val right: Int,
    val bottom: Int
)

data class InitialMargin(
    val left: Int,
    val top: Int,
    val right: Int,
    val bottom: Int
)
fun View.recordInitialPadding() = InitialPadding(left, top, right, bottom)
fun View.recordInitialMargin(): InitialMargin {
    val lp = layoutParams as? ViewGroup.MarginLayoutParams
    return InitialMargin(
        lp?.leftMargin ?: 0,
        lp?.topMargin ?: 0,
        lp?.rightMargin ?: 0,
        lp?.bottomMargin ?: 0
    )
}
fun View.doOnApplyWindowInsets(block: (insets: WindowInsetsCompat, initialPadding: InitialPadding, initialMargin: InitialMargin) -> Unit) {
    val initialPadding = recordInitialPadding()
    val initialMargin = recordInitialMargin()
    ViewCompat.setOnApplyWindowInsetsListener(this) { _, insets ->
        block(insets, initialPadding, initialMargin)
        insets
    }
    requestApplyInsetsWhenAttached()
}

fun View.requestApplyInsetsWhenAttached() {
    if (isAttachedToWindow) {
        requestApplyInsets()
    } else {
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                v.removeOnAttachStateChangeListener(this)
                v.requestApplyInsets()
            }

            override fun onViewDetachedFromWindow(v: View) = Unit
        })
    }
}
fun Activity.isGestureNavigation(navigationBarInsets: Insets): Boolean {
    val threshold = (20 * this.resources.displayMetrics.density).toInt()
    return navigationBarInsets.bottom <= threshold.coerceAtLeast(44)
}

class SystemBarConfig {
    var decorFitsSystemWindows = false
    var statusBarColor = Color.TRANSPARENT
    var navigationBarColor = Color.TRANSPARENT
    var statusBarBlackFont = false
    var navigationBarBlackIcon = false
    var gestureNavigationTransparent = true
    var navigationTransparent = false
    var applyStatusBarView: View? = null
    var applyNavigationBarView: View? = null

    operator fun component1() = decorFitsSystemWindows
    operator fun component2() = statusBarColor
    operator fun component3() = navigationBarColor
    operator fun component4() = statusBarBlackFont
    operator fun component5() = navigationBarBlackIcon
    operator fun component6() = gestureNavigationTransparent
    operator fun component7() = navigationTransparent
    operator fun component8() = applyStatusBarView
    operator fun component9() = applyNavigationBarView
}