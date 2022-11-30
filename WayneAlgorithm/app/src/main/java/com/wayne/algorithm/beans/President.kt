package com.wayne.algorithm.beans

import android.view.View
import android.view.View.OnClickListener

class President(name:String) {
    val safeName = name
    get() = if(field == "Beet") "Wayne" else field
    companion object{
        val mListener:OnClickListener = object :OnClickListener{
            override fun onClick(p0: View?) {
                TODO("Not yet implemented")
            }

        }
    }
}