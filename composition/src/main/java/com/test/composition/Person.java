package com.test.composition;

public class Person {
    private Job job;

    public Person() {
        this.job = new Job();
        job.setSalary(2850.50);
    }

    public double getSalary() {
        return job.getSalary();
    }

}
