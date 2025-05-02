package com.test.composition;

public class TestPerson {
    public static void main(String[] args) {
        Person person = new Person();
        double salary = person.getSalary();

        System.out.println("Salary is: " + salary);
    }
}
