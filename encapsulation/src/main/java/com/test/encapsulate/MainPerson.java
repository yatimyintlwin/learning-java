package com.test.encapsulate;

import com.test.encapsulate.entities.Person;

public class MainPerson {
    public static void main(String[] args) {
        Person person = new Person();

        person.setName("Bob");
        person.setAge(22);
        person.setCountry("Myanmar");

        System.out.println("Name is: " + person.getName());
        System.out.println("Age is: " + person.getAge());
        System.out.println("Country is: " + person.getCountry());

        person.setName("Alex");
        System.out.println("Name is: " + person.getName());
        System.out.println("Age is: " + person.getAge());
    }
}
