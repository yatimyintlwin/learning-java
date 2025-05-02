package com.test.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ListExample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add("Orange");
        list.add("Banana");
        list.add("Apple");

        String secondElement = list.get(1);

        list.set(1, "Cherry");

        list.add("Blueberry");

        list.remove(2);

        int size = list.size();

        System.out.println(list);

        System.out.println(size);

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        System.out.println("==================");

        Collections.sort(list);

        for (String s : list) {
            System.out.println(s);
        }


    }
}
