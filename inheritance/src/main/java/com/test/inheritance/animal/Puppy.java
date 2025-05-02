package com.test.inheritance.animal;

class Puppy extends Dog {
    @Override
    public void makeSound() {
        System.out.println("The puppy can barks: Woof Woof!");
    }
}
