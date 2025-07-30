package org.example;

import java.time.LocalDate;

public class DatedTask extends Task {
    protected LocalDate dueDate;

    public DatedTask(String title, String description, User user, LocalDate dueDate) {
        super(title, description, user);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return super.toString() + " | Échéance: " + dueDate;
    }
}