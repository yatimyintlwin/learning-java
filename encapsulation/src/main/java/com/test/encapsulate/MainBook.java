package com.test.encapsulate;

import com.test.encapsulate.entities.Book;

public class MainBook {
    public static void main(String[] args) {
        Book book = new Book();

        book.setTitle("Java Programming");
        book.setAuthor("Bob");
        book.setPrice(35.0);

        book.applyDiscount(2.5);

        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Price: $" + book.getPrice());
    }
}
