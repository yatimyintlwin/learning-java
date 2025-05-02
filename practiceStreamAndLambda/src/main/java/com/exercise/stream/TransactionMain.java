package com.exercise.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionMain {
    public static void main(String[] args) {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("Grocery", 50.0),
                new Transaction("Grocery", 30.0),
                new Transaction("Shopping", 100.0),
                new Transaction("Shopping", 150.0),
                new Transaction("Grocery", 20.0)
        );

        Map<String, Double> totalAmountByType = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getType, Collectors.summingDouble(Transaction::getAmount)));

        System.out.println(totalAmountByType);
    }
}
