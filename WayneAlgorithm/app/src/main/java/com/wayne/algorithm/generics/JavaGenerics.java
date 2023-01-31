package com.wayne.algorithm.generics;

import java.util.ArrayList;
import java.util.List;

public class JavaGenerics<T extends Student>{

    private final T entity;
    public JavaGenerics(T entity, T entity1){
        this.entity = entity1;
    }

    public void test(){
        // Java
//        List<String> strs = new ArrayList<String>();
//        List<Object> objs = strs; // !!! A compile-time error here saves us from a runtime exception later.
//        objs.add(1); // Put an Integer into a list of Strings
//        String s = strs.get(0); // !!! ClassCastException: Cannot cast Integer to String
    }
}
