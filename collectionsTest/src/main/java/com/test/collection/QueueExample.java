package com.test.collection;

import java.util.LinkedList;
import java.util.Queue;

public class QueueExample {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();

        queue.add("Element1");
        queue.add("Element2");
        queue.add("Element3");

        queue.offer("Element4");

        System.out.println("Queue: " + queue);

        String firstElement = queue.peek();
        System.out.println("Peek: " + firstElement);

        String polledElement = queue.poll();
        System.out.println("Poll: " + polledElement);

        System.out.println("Queue after poll: " + queue);
    }
}
