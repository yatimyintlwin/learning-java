package com.test.inheritance.animal;

public class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("The cat meows: Meow Meow!");
    }
}
