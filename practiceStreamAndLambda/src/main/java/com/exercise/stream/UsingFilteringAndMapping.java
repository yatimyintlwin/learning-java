package com.exercise.stream;

import java.util.Arrays;
import java.util.List;

public class UsingFilteringAndMapping {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = numbers.stream().filter(n -> n % 2 == 0)
                .map(n -> n * n).toList();

        System.out.println(squares);
    }
}
