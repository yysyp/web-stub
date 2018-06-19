package com.ddm.generictry;

/**
 * Created by yunpeng.song on 6/19/2018.
 */
public class Cat extends Animal {

    public Cat(String name) {
        super(name);
    }

    public void jump(){
        System.out.println(getName() + " can jump.");
    }
}