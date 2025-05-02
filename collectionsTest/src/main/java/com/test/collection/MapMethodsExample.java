package com.test.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapMethodsExample {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();

        map.put("Apple", 1);
        map.put("Banana", 2);
        map.put("Orange", 3);
        map.put("Berry", 4);

        Integer value = map.get("Banana");
        System.out.println("Value for 'Banana': " + value);

        map.remove("Orange");

        // containsKey(Object key) - Checks if the map contains the specified key
        boolean hasApple = map.containsKey("Apple");
        System.out.println("Contains 'Apple': " + hasApple);

        Set<String> keys = map.keySet();
        System.out.println("Keys in map: " + keys);
    }
}
