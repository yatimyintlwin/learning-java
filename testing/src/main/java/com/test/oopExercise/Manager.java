package com.test.oopExercise;

public class Manager extends Employee {

    Manager(String name, int id, double salary) {
        super(name, id, salary);
    }

    @Override
    public double calculateBonus() {
        return 0.2 * getSalary();
    }
}
