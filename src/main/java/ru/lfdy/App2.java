package ru.lfdy;

public class App2 {
    public static void main(String[] args) {
        System.out.println("Hello World");
        System.err.println("Initial SessionFactory creation failed.");
        throw new RuntimeException("Initial SessionFactory creation failed.");
    }
}
