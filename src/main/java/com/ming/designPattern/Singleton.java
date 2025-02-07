package com.ming.designPattern;

/**
 * @author Shukun.Li
 */
public class Singleton {

    private Singleton(){

    }

    private static Singleton singleton = new Singleton();

    public static Singleton getInstance(){
        return singleton;
    }

    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();

        System.out.println(singleton1 == singleton2);
    }
}
