package com.example.todolist.config;

import com.example.todolist.model.Task;
import com.example.todolist.model.User;
import com.example.todolist.repository.TaskRepository;
import com.example.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void run(String... args) throws Exception {
        // Create users
        User user1 = new User("jean_dupont", "jean.dupont@example.com", "Jean Dupont");
        User user2 = new User("marie_martin", "marie.martin@example.com", "Marie Martin");
        User user3 = new User("pierre_bernard", "pierre.bernard@example.com", "Pierre Bernard");
        
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        // Create tasks for user1
        Task task1 = new Task("Terminer le tutoriel Spring Boot", "Apprendre les bases du framework Spring Boot", user1);
        task1.setPriority(Task.TaskPriority.HIGH);
        task1.setStatus(Task.TaskStatus.IN_PROGRESS);
        
        Task task2 = new Task("Réviser la documentation Java", "Lire les nouvelles fonctionnalités de Java 17", user1);
        task2.setPriority(Task.TaskPriority.MEDIUM);
        
        Task task3 = new Task("Corriger le bug d'authentification", "Les utilisateurs ne peuvent pas se connecter avec des caractères spéciaux dans le mot de passe", user1);
        task3.setPriority(Task.TaskPriority.HIGH);
        task3.setStatus(Task.TaskStatus.COMPLETED);

        // Create tasks for user2
        Task task4 = new Task("Écrire des tests unitaires", "Ajouter des tests unitaires pour UserService", user2);
        task4.setPriority(Task.TaskPriority.HIGH);
        
        Task task5 = new Task("Mettre à jour la documentation du projet", "Ajouter la documentation des endpoints API", user2);
        task5.setPriority(Task.TaskPriority.LOW);
        
        Task task6 = new Task("Revue de code", "Examiner la pull request #123", user2);
        task6.setPriority(Task.TaskPriority.MEDIUM);
        task6.setStatus(Task.TaskStatus.IN_PROGRESS);

        // Create tasks for user3
        Task task7 = new Task("Déployer en production", "Déployer la version 2.0 sur le serveur de production", user3);
        task7.setPriority(Task.TaskPriority.HIGH);
        
        Task task8 = new Task("Optimisation de la base de données", "Optimiser les requêtes lentes dans la table des utilisateurs", user3);
        task8.setPriority(Task.TaskPriority.MEDIUM);
        task8.setStatus(Task.TaskStatus.COMPLETED);

        // Save all tasks
        taskRepository.save(task1);
        taskRepository.save(task2);
        taskRepository.save(task3);
        taskRepository.save(task4);
        taskRepository.save(task5);
        taskRepository.save(task6);
        taskRepository.save(task7);
        taskRepository.save(task8);

        System.out.println("Données initiales chargées avec succès !");
    }
}