package org.example;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Quel est votre prénom ? ");
        String prenom = scanner.nextLine();
        User user = new User(prenom);

        System.out.println("Hello " + prenom + "!");

        ArrayList<Task> tasks = new ArrayList<>();
        boolean exit = false;

        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1. Lister les tâches");
            System.out.println("2. Ajouter une tâche");
            System.out.println("3. Supprimer une tâche");
            System.out.println("4. Modifier une tâche");
            System.out.println("5. Quitter");
            System.out.print("Choisissez une option : ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    listTasks(tasks);
                    break;
                case "2":
                    addTask(scanner, tasks, user);
                    break;
                case "3":
                    deleteTask(scanner, tasks);
                    break;
                case "4":
                    modifyTask(scanner, tasks);
                    break;
                case "5":
                    exit = true;
                    break;
                default:
                    System.out.println("Option invalide.");
            }
        }

        scanner.close();
    }

    private static void listTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Aucune tâche à afficher.");
            return;
        }

        System.out.println("\nListe des tâches :");
        int i = 1;
        for (Task task : tasks) {
            System.out.println(i + ". " + task);
            i++;
        }
    }

    private static void addTask(Scanner scanner, ArrayList<Task> tasks, User user) {
        System.out.print("Titre de la tâche: ");
        String title = scanner.nextLine();
        System.out.print("Description de la tâche: ");
        String description = scanner.nextLine();
        System.out.print("Voulez-vous ajouter une date d'échéance ? (oui/non) : ");
        String reponse = scanner.nextLine().trim().toLowerCase();

        if (reponse.equals("oui") || reponse.equals("o")) {
            System.out.print("Date d'échéance (YYYY-MM-DD) : ");
            String dateStr = scanner.nextLine();
            try {
                LocalDate date = LocalDate.parse(dateStr);
                tasks.add(new DatedTask(title, description, user, date));
                System.out.println("Tâche avec date ajoutée.");
            } catch (Exception e) {
                System.out.println("Date invalide, tâche normale ajoutée.");
                tasks.add(new Task(title, description, user));
            }
        } else {
            tasks.add(new Task(title, description, user));
            System.out.println("Tâche ajoutée.");
        }
    }

   private static void deleteTask(Scanner scanner, ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Aucune tâche à supprimer.");
            return; // ⛔ On sort directement
        }

        listTasks(tasks);
        System.out.print("Numéro de la tâche à supprimer : ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (index >= 0 && index < tasks.size()) {
                tasks.remove(index);
                System.out.println("Tâche supprimée.");
            } else {
                System.out.println("Numéro invalide.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrée non valide.");
        }
    }

    private static void modifyTask(Scanner scanner, ArrayList<Task> tasks) {
        listTasks(tasks);
        System.out.print("Numéro de la tâche à modifier : ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (index < 0 || index >= tasks.size()) {
                System.out.println("Numéro invalide.");
                return;
            }

            Task task = tasks.get(index);

            System.out.print("Nouveau titre (laisser vide pour ne pas changer) : ");
            String newTitle = scanner.nextLine();
            if (!newTitle.isEmpty()) task.title = newTitle;

            System.out.print("Nouvelle description (laisser vide pour ne pas changer) : ");
            String newDesc = scanner.nextLine();
            if (!newDesc.isEmpty()) task.description = newDesc;

            System.out.print("Changer le statut ? (oui/non) : ");
            if (scanner.nextLine().trim().toLowerCase().startsWith("o")) {
                System.out.print("La tâche est-elle faite ? (oui/non) : ");
                task.done = scanner.nextLine().trim().toLowerCase().startsWith("o");
            }

            if (task instanceof DatedTask) {
                System.out.print("Changer la date d’échéance ? (oui/non) : ");
                if (scanner.nextLine().trim().toLowerCase().startsWith("o")) {
                    System.out.print("Nouvelle date (YYYY-MM-DD) : ");
                    String dateStr = scanner.nextLine();
                    try {
                        ((DatedTask) task).dueDate = LocalDate.parse(dateStr);
                    } catch (Exception e) {
                        System.out.println("Date invalide.");
                    }
                }
            } else {
                System.out.print("Ajouter une date d’échéance ? (oui/non) : ");
                if (scanner.nextLine().trim().toLowerCase().startsWith("o")) {
                    System.out.print("Date (YYYY-MM-DD) : ");
                    String dateStr = scanner.nextLine();
                    try {
                        LocalDate date = LocalDate.parse(dateStr);
                        DatedTask datedTask = new DatedTask(task.title, task.description, task.user, date);
                        datedTask.done = task.done;
                        tasks.set(index, datedTask);
                    } catch (Exception e) {
                        System.out.println("Date invalide.");
                    }
                }
            }

            System.out.println("Tâche modifiée.");
        } catch (NumberFormatException e) {
            System.out.println("Entrée non valide.");
        }
    }
}