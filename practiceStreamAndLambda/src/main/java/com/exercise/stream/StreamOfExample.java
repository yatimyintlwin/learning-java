package com.exercise.stream;

import java.util.stream.Stream;

public class StreamOfExample {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("Apple", "Orange", "Banana", "Cherry");
        stream.forEach(System.out::println);
    }
}
