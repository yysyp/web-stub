package com.ddm.generictry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunpeng.song on 6/19/2018.
 */
public class TestAnimal {
    public static void main(String[] args) {
        AnimalTrainer animalTrainer = new AnimalTrainer();
        //Test 1
        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Cat("cat1"));
        animalList.add(new Bird("bird1"));

        animalTrainer.act(animalList);	//可以通过编译

        //Test 2
        List<Cat> catList = new ArrayList<>();
        catList.add(new Cat("cat2"));
        catList.add(new Cat("cat3"));

      //!!  animalTrainer.act(catList);  //Error //无法通过编译

        animalTrainer.act1(catList);  //OK

    }

    public void testAdd(List<? extends Animal> list) {
        //"?" can be one of Animal subclass so may not match any other of Animal's subclasses.
        //!!  list.add(new Animal("animal")); //Error
        //!!  list.add(new Bird("bird")); //Error
        //!!  list.add(new Cat("cat")); //Error
        list.add(null); //OK
    }


    public void testAdd1(List<? super Bird> list) {
        //Same as testAdd, "?" can be one of super class of Bird, so may not match Animal.
        list.add(new Bird("bird")); //Error
        list.add(new Magpie("magpie")); //Ok
        //!!  list.add(new Animal("animal")); //Error
        //!!  list.add(new Cat("cat")); //Error
        list.add(null); //OK
    }

}