package org.example;

public class User {
    private static int counter;
    private int id;
    private String name;

    static {
        counter = 0;
    }

    {
        id = ++counter;
    }

    public User(String name) {
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
}