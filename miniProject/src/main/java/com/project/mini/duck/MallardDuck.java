package com.project.mini.duck;

public class MallardDuck extends Duck
        implements Quackable, Flyable, Eatable, Swimable {

    @Override
    public void quack() {
        System.out.println("MallardDuck can quack");
    }

    @Override
    public void fly() {
        System.out.println("MallardDuck can fly");
    }

    @Override
    public void eat() {
        System.out.println("MallardDuck can eat");
    }

    @Override
    public void swim() {
        System.out.println("MallardDuck can swim");
    }
}
