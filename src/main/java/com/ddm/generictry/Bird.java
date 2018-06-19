package com.ddm.generictry;

/**
 * Created by yunpeng.song on 6/19/2018.
 */
public class Bird extends Animal {

    public Bird(String name) {
        super(name);
    }

    public void fly(){
        System.out.println(getName() + " can fly.");
    }
}