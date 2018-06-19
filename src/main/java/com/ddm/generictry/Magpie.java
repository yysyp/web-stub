package com.ddm.generictry;

/**
 * Created by yunpeng.song on 6/19/2018.
 */
public class Magpie extends Bird {

    public Magpie(String name) {
        super(name);
    }

    public void sing(){
        System.out.println(getName() +
                " can not only eat,but sing");
    }
}
