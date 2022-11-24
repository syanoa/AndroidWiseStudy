package com.wayne.algorithm;

import android.util.Log;

/**
 * a lazy-loaded singleton
 * "Initialization-on-demand holder"
 */
public class JavaSingletonStaticNestedClassWay {
    private JavaSingletonStaticNestedClassWay(){
        Log.d("wayne_check", "JavaSingletonStaticNestedClassWay 1s");
    }
    static {
        Log.d("wayne_check", "the time of static block running for JavaSingletonStaticNestedClassWay");
    }
    private static class LazyHolder {
        public static final JavaSingletonStaticNestedClassWay instance = new JavaSingletonStaticNestedClassWay();
    }
    public static JavaSingletonStaticNestedClassWay getInstance(){
        return LazyHolder.instance;
    }
}
