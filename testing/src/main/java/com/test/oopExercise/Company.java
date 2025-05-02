package com.test.oopExercise;

import java.util.List;

public class Company {
    private List<Employee> employees;

    public void addEmployee(Employee e) {
        employees.add(e);
    }

    public void showAllEmployees() {
        for (Employee e : employees) {
            e.displayDetails();
        }
    }

}
