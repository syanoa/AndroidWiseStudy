package com.wayne.algorithm.modifiervisibilities;

public class JavaPrivateTester {
    class InnerClass{
        private String innerName = "InnerClass";
    }
    void perform(){
       new InnerClass().innerName = "Change the name";//Java can access private property of an inner class from outside, which Kotlin can't.
    }
}
