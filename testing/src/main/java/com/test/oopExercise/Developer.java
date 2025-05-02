package com.test.oopExercise;

public class Developer extends Employee{

    Developer(String name, int id, double salary) {
        super(name, id, salary);
    }

    @Override
    public double calculateBonus() {
        return 0.1 * getSalary();
    }
}
