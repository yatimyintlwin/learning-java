package com.exercise.lambda;

import java.util.ArrayList;

public class Example1 {
    public static void main(String[] args) {
        ArrayList<Integer> nums = new ArrayList<Integer>();
        nums.add(5);
        nums.add(4);
        nums.add(3);
        nums.add(2);
        nums.add(1);

        nums.forEach(System.out::println);

//        nums.forEach((n) -> {
//            System.out.println(n);
//        });
    }
}
