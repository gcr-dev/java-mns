package org.example;

public class Task {
    private static int counter;
    private int id;
    private String title;
    private User user;
    private String description;
    

    static {
        counter = 0;
    }

    {
        id = ++counter;
    }

    public Task(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; } 
    public User getUser() { return user; }
}