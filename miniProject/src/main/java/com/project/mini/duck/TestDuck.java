package com.project.mini.duck;

public class TestDuck {
    public static void main(String[] args) {
        MallardDuck mallardDuck = new MallardDuck();

        mallardDuck.setName("MallardDuck");
        mallardDuck.setColor("White");
        mallardDuck.setAge(2);
        mallardDuck.display();
        mallardDuck.quack();
        mallardDuck.fly();
        mallardDuck.eat();
        mallardDuck.swim();

        System.out.println("------------------------------------");

        RubberDuck rubberDuck = new RubberDuck();

        rubberDuck.setName("RubberDuck");
        rubberDuck.setColor("Yellow");
        rubberDuck.setAge(1);
        rubberDuck.display();
        rubberDuck.swim();
    }
}
