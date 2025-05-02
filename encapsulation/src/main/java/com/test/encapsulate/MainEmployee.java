package com.test.encapsulate;

import com.test.encapsulate.entities.Employee;

public class MainEmployee {
    public static void main(String[] args) {
        Employee employee = new Employee();

        employee.setName("John Doe");
        employee.setAge(30);
        employee.setSalary(50000);

        System.out.println("Employee Name: " + employee.getName());
        System.out.println("Employee Age: " + employee.getAge());
        System.out.println("Employee Salary: " + employee.getSalary());
    }
}
