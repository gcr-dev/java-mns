package org.example;
import java.util.*;

public class DatabaseAccess {
    private static DatabaseAccess instance;
    private List<User> users;
    private List<Task> tasks;

    private DatabaseAccess() {
        users = new ArrayList<>();
        tasks = new ArrayList<>();
        // Initialisation simulée
        users.add(new User("Alice"));
        users.add(new User("Bob"));
        tasks.add(new TaskBuilder().setTitle("Acheter du lait").setDescription("Aller à l'épicerie et acheter du lait").setUser(users.get(0)).build());
        tasks.add(new TaskBuilder().setTitle("Finir le projet").setDescription("Terminer la TODO List avant la deadline").setUser(users.get(1)).build());
    }

    public static synchronized DatabaseAccess getInstance() {
        if (instance == null) {
            instance = new DatabaseAccess();
        }
        return instance;
    }

    // User methods
    public User findUserById(int id) throws NotFoundException {
        return users.stream().filter(u -> u.getId() == id).findFirst()
            .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(int id) throws NotFoundException {
        User user = findUserById(id);
        users.remove(user);
    }

    // Task methods
    public Task findTaskById(int id) throws NotFoundException {
        return tasks.stream().filter(t -> t.getId() == id).findFirst()
            .orElseThrow(() -> new NotFoundException("Task not found"));
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int id) throws NotFoundException {
        Task task = findTaskById(id);
        tasks.remove(task);
    }

    public List<User> getUsers() { return users; }
    public List<Task> getTasks() { return tasks; }
}