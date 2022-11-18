package com.wayne.algorithm.activities

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.PersistableBundle
import android.view.ViewGroup
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.FragmentActivity
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.wayne.LockScreenOrientation
import com.wayne.algorithm.ktxs.tryFold
import com.wayne.algorithm.ktxs.tryReduce
import com.wayne.algorithm.ktxs.wayneLogd
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WayneComposeActivity:FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(ComposeView(this).apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            setContent {
                LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setSystemBarsColor(Color.Transparent)
                }
                MaterialTheme {
//                    CompositionLocalProvider(
//                        LocalThemeColors provides colors,
//                        LocalSystemUiController provides systemUiController,
//                        content = content
//                    )
                    AppContent()
                }
            }
        })
    }
}

@Composable
fun AppContent(){
    LaunchedEffect(true){
        val (foldRes, reduceRes) = listOf(1000, 100, 10, 1).let {
            arrayOf(it.tryFold(), it.tryReduce())
        }
        wayneLogd(" foldRes -> $foldRes    reduceRes -> $reduceRes")
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Red)) {

    }
}
