package com.test.collection;

import java.util.HashSet;
import java.util.Set;

public class SetExample {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();

        set.add("Blueberry");
        set.add("Orange");
        set.add("Berry");

        set.remove("Berry");

        System.out.println(set);

        boolean containOrange = set.contains("Orange");
        System.out.println("Contain Orange: " + containOrange);

        int size = set.size();
        System.out.println(size);
    }
}
