package com.test.inheritance.animal;

public class Main {
    public static void main(String[] args) {
        Animal myDog = new Dog();
        Animal myPuppy = new Puppy();
        Animal myCat = new Cat();

        myDog.makeSound();
        myDog.eat();
        myPuppy.makeSound();
        myCat.makeSound();
    }
}