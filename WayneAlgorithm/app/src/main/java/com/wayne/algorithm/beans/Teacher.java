package com.wayne.algorithm.beans;

import android.util.Log;
import android.view.View;

public class Teacher {

    static {
        Log.d("wayne_check", "static block in Teacher");
    }
    public Teacher(String name) {
        Log.d("wayne_check", "1st constructor in Teacher");
    }

    public Teacher(String name, int id) {
        this(name);
        Log.d("wayne_check", "2nd constructor in Teacher");
    }
}
