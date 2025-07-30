package org.example;

public class Main {
    public static void main(String[] args) {
        DatabaseAccess db = DatabaseAccess.getInstance();

        // Afficher les users
        for (User u : db.getUsers()) {
            System.out.println("User: " + u.getId() + " - " + u.getName());
        }

        // Afficher les tasks
        for (Task t : db.getTasks()) {
            System.out.println("Task: " + t.getId() + " - " + t.getTitle() + " (User: " + t.getUser().getName() + ")");
        }

        for (Task t : db.getTasks()) {
            System.out.println("Task: " + t.getId() + " - " + t.getTitle() + " (" + t.getDescription() + ") (User: " + t.getUser().getName() + ")");
        }

        // Ajouter un nouvel utilisateur
        User newUser = new User("Charlie");
        db.addUser(newUser);

        // Ajouter une nouvelle tâche avec le TaskBuilder
        Task newTask = new TaskBuilder().setTitle("Réviser Java").setUser(newUser).build();
        db.addTask(newTask);

        System.out.println("Après ajout :");
        for (Task t : db.getTasks()) {
            System.out.println("Task: " + t.getId() + " - " + t.getTitle() + " (User: " + t.getUser().getName() + ")");
        }
    }
}