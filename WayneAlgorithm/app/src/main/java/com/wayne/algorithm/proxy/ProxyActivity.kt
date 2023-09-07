package com.wayne.algorithm.proxy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.wayne.algorithm.R

class ProxyActivity : AppCompatActivity() {
    private val a = ProxyApiImpl()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proxy)
        val aProxy = ProxyManager.createProxyImpl<ProxyApi>(a)

        findViewById<Button>(R.id.button1).setOnClickListener {
            aProxy.doSomething1()
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            aProxy.doSomething2()
        }
    }
}