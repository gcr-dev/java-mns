package org.example;

import java.util.UUID;

public class Task {
    private final UUID id;
    protected String title;
    protected String description;
    protected boolean done;
    protected User user;

    public Task(String title, String description, User user) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.user = user;
        this.done = false;
    }

    public UUID getId() {
        return id;
    }

    public String toString() {
        return (done ? "[x] " : "[ ] ") + title + ": " + description + " (id: " + id + ")";
    }
}