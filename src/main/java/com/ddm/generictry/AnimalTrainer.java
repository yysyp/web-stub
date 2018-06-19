package com.ddm.generictry;

import java.util.List;

/**
 * Created by yunpeng.song on 6/19/2018.
 */
public class AnimalTrainer {
    public void act(List<Animal> list) {
        for (Animal animal : list) {
            animal.eat();
        }
    }

    public void act1(List<? extends Animal> list) {
        for (Animal animal : list) {
            animal.eat();
        }
    }
}
