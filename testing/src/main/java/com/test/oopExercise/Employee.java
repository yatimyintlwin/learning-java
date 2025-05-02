package com.test.oopExercise;

public abstract class Employee {
    String name;
    int id;
    double salary;

    public Employee(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void displayDetails() {
        System.out.println("ID: " + id + "/nName: " + name + "/nSalary: " + salary + "/nBonus: " + calculateBonus());
    }

    public abstract double calculateBonus();
}
