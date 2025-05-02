package com.project.mini.duck;

public class RubberDuck extends Duck implements Swimable{

    @Override
    public void swim() {
        System.out.println("RubberDuck can swim");
    }
}
