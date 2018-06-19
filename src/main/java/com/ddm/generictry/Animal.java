package com.ddm.generictry;

/**
 * Created by yunpeng.song on 6/19/2018.
 */
public class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public void eat() {
        System.out.println(getName() + " can eat.");
    }

    public String getName(){
        return name;
    }
}
