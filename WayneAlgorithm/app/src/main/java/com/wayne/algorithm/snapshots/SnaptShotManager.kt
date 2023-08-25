package com.wayne.algorithm.snapshots

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import com.wayne.algorithm.ktxs.wayneLogd

class SnaptShotManager {

    fun doSomething(){
        var variable by mutableStateOf("1")
        val snapshot = Snapshot.takeSnapshot()
        variable = "3"
        try{
            snapshot.enter {
                wayneLogd("check the variable inside the snapshot enter -> $variable")
            }
        }finally {
            snapshot.dispose()
        }
        wayneLogd("check the variable outside -> $variable")
    }
}