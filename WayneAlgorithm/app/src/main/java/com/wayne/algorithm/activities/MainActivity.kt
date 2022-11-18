package com.wayne.algorithm.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wayne.algorithm.R
import com.wayne.algorithm.ktxs.wayneLogd
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        wayneLogd("1st activity")
        setContentView(R.layout.activity_main)
    }
}