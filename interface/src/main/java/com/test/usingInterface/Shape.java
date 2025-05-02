package com.test.usingInterface;

interface Shape {
    void draw();
}

class Circle implements Shape {
    public void draw() {
        System.out.println("Drawing a com.test.usingInterface.Circle");
    }
}

class Rectangle implements Shape {
    public void draw() {
        System.out.println("Drawing a com.test.usingInterface.Rectangle");
    }
}