package com.test.service;
@FunctionalInterface
public interface TestInterface {
    int msg();
    //Multiple non-overriding abstract methods found in service main.MyInterface  @FunctionalInterface不能定义多个非重写抽象方法
    //int msg2();

    //java8规定类中的方法优先级要高于接口中的默认方法，所以接口中默认方法复写Object类中的方法是没有意义的，
    // 因为所有的接口都默认继承自Object类使得默认方法一定会被覆盖。
    //https://stackoverflow.com/questions/24016962/java8-why-is-it-forbidden-to-define-a-default-method-for-a-method-from-java-lan
    /*default boolean equals(Object obj){
        return true;
    }*/
    boolean equals(Object obj);
}
