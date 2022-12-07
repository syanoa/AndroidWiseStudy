package com.wayne.algorithm.generics;

public class JavaGenerics<T extends Student>{

    private final T entity;
    public JavaGenerics(T entity, T entity1){
        this.entity = entity1;
    }
}
