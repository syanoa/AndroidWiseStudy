package com.wayne.algorithm;

public class JavaSingletonStaticNestedClassWay {
    private static class SingletonHolder{
        public static final JavaSingletonStaticNestedClassWay instance = new JavaSingletonStaticNestedClassWay();
    }
    public static JavaSingletonStaticNestedClassWay getInstance(){
        return SingletonHolder.instance;
    }
}
