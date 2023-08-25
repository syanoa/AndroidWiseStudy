package com.wayne.algorithm.activities

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.wayne.LockScreenOrientation
import com.wayne.algorithm.JavaSingletonStaticNestedClassWay
import com.wayne.algorithm.R
import com.wayne.algorithm.SingletonByLazy
import com.wayne.algorithm.SingletonWithParameter
import com.wayne.algorithm.beans.Student
import com.wayne.algorithm.beans.Teacher
import com.wayne.algorithm.ktxs.*
import com.wayne.algorithm.modifiervisibilities.JavaProtectedTester
import com.wayne.algorithm.snapshots.SnaptShotManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow

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
                    AppNav()
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNav(){
    val navController = rememberNavController()
    NavHost(navController = navController, "1st"){
        router("1st"){
            AppContent(navController)
        }
        router("2nd"){
            App2()
        }
    }

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppContent(navController: NavHostController){
    val ctx = LocalContext.current as Activity
    var rememberV by remember {
        mutableStateOf(0)
    }
    var rememberInput = remember {
        rememberV
    }
    var rememberChanges = remember {
        0
    }
    val rememberUpdatedState by rememberUpdatedState(rememberV)
    LaunchedEffect(Unit){
       SnaptShotManager().doSomething()
    }

//    LaunchedEffect(true){
//        val (foldRes, reduceRes) = listOf(1000, 100, 10, 1).let {
//            arrayOf(it.tryFold(), it.tryReduce())
//        }
//        wayneLogd(" foldRes -> $foldRes    reduceRes -> $reduceRes")
//        Student("Wayne")
//        Teacher("PlayStation", 5)
//        val singletonWithParameter = SingletonWithParameter.getInstance(ctx)
//
//    }
//    LaunchedEffect(state1){
//        wayneLogd("launched effect state1 and report state2 -> $state2")
//    }
//    LaunchedEffect(state2){
//        wayneLogd("launched effect state2-> $state2")
//        wayneLogd("launched effect state3-> $state3")
//    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Red),  horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "rememberInput: $rememberInput")
        Text(text = "rememberUpdatedState: $rememberUpdatedState")
        Button(onClick = { rememberV += 1 }) {
            Text(text = "btn change rememberV -> $rememberV")
        }
        Button(onClick = { ctx.toNextPage(SecondActivity::class.java)}) {
            Text(text = "to next activity")
        }
        Button(onClick = { navController.navigate("2nd")}) {
            Text(text = "to 2nd composable")
        }
        Button(onClick = {
            rememberChanges++
        }) {
            Text(text = "change the high order functions")
        }
        DisposableEffectLearning(onStart = {
            wayneLogd("disposableEffect onStart value $rememberChanges")
        }, onStop = {
            wayneLogd("disposableEffect onStop value $rememberChanges")
        })
    }
}
@Composable
fun DisposableEffectLearning(onStart:()->Unit, onStop:()->Unit){
    val lifeCycleOwner = LocalLifecycleOwner.current
//    val currentOnStart by rememberUpdatedState(newValue = onStart)
//    val currentOnStop by rememberUpdatedState(newValue = onStop)
    DisposableEffect(lifeCycleOwner){

        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
//                currentOnStart()
                onStart()
            } else if (event == Lifecycle.Event.ON_STOP) {
//                currentOnStop()
                onStop()
            }else if(event == Lifecycle.Event.ON_DESTROY){
                wayneLogd("disposableEffect lifecycle onDestroy")
            }
        }
            wayneLogd("disposableEffect addLifecycleObserver ")
        lifeCycleOwner.lifecycle.addObserver(observer)
        onDispose {
            wayneLogd("disposableEffect removeLifecycleObserver ")
            lifeCycleOwner.lifecycle.removeObserver(observer)
        }
    }
    Text(text = "Inner text")
}
class SomeFactory{
    suspend fun produceSomething():String = withContext(Dispatchers.IO){
         "brand new thing"
    }
}
@Composable
fun getStateFromNonState():State<String>{
    val ctx = LocalContext.current
    val factory = remember {
        SomeFactory()
    }
    return produceState<String>(initialValue = "", factory){
        val result = factory.produceSomething()
        value = result
    }
}
@Composable
fun App2(){
   val fromNonState = getStateFromNonState()
}