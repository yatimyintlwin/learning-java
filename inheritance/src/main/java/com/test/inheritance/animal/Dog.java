package com.test.inheritance.animal;

public class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("The dog barks: Woof Woof!");
    }

    @Override
    public void eat() {
        super.eat();
        System.out.println("The dog can eat");
    }

}
