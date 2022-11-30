package com.wayne.algorithm.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.wayne.algorithm.JavaSingletonStaticNestedClassWay
import com.wayne.algorithm.R
import com.wayne.algorithm.SingletonByLazy
import com.wayne.algorithm.SingletonWithParameter
import com.wayne.algorithm.beans.Student
import com.wayne.algorithm.beans.Teacher
import com.wayne.algorithm.ktxs.tryFold
import com.wayne.algorithm.ktxs.tryReduce
import com.wayne.algorithm.ktxs.wayneLogd
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var test: JavaSingletonStaticNestedClassWay? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        wayneLogd("it's 1st activity")
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
//        setContentView(R.layout.activity_main)
//        test = JavaSingletonStaticNestedClassWay.getInstance()
//        SingletonWithParameter.getInstance(this)
//        SingletonByLazy.instance
//        runBlocking {
//            val scope0 = this
//
//            scope0.launch {
//               val scope1 = this
//                scope1.launch {
//                    coroutineContext[Job]
//                }
//            }
//
//        }

    }
}

@Composable
fun AppContent(){
    val ctx = LocalContext.current
    LaunchedEffect(true){
        val (foldRes, reduceRes) = listOf(1000, 100, 10, 1).let {
            arrayOf(it.tryFold(), it.tryReduce())
        }
        wayneLogd(" foldRes -> $foldRes    reduceRes -> $reduceRes")
        Student("Wayne")
        Teacher("PlayStation", 5)
        val singletonWithParameter = SingletonWithParameter.getInstance(ctx)

    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Red)) {

    }
}