package com.exercise.stream;

import java.util.Arrays;
import java.util.List;

public class SummingIntegers {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = numbers.stream().reduce(0, Integer::sum);   //.reduce(0, (a, b) -> a + b)
        System.out.println(sum);
    }
}