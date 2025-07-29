package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // La création d'un objet Scanner va permettre de lire les entrées de l'utilisateur
        Scanner scanner = new Scanner(System.in);

        System.out.print("Quel est votre prénom ? ");

        // Lecture de l'entrée 
        String prenom = scanner.nextLine();

        // J'affiche hello + le prénom
        System.out.println("Hello " + prenom + "!");

        // Création d'un tableau contenant une liste de tâches
        String[] tasks = new String[] {
            "Faire les courses",
            "Étudier Java",
        };

        // On affiche la liste des tâches en bouclant
        System.out.println("\nListe de tâches actuelle :");
        for (int i = 0; i < tasks.length; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }

        // Demande à l'utilisateur d'ajouter une nouvelle tâche
        System.out.print("\nAjouter une nouvelle tâche : ");

        // Lecture de la nouvelle tâche
        String nouvelleTache = scanner.nextLine();

        // Comme les tableaux Java ont une taille fixe, on crée un nouveau tableau plus grand pour y ajouter la nouvelle tâche
        String[] nouvellesTasks = new String[tasks.length + 1];

        // Copie des anciennes tâches dans le nouveau tableau
        for (int i = 0; i < tasks.length; i++) {
            nouvellesTasks[i] = tasks[i];
        }

        // Ajout de la nouvelle tâche à la fin du tableau
        nouvellesTasks[tasks.length] = nouvelleTache;

        // Affichage de la liste mise à jour avec la nouvelle tâche
        System.out.println("\nListe de tâches mise à jour :");
        for (int i = 0; i < nouvellesTasks.length; i++) {
            System.out.println((i + 1) + ". " + nouvellesTasks[i]);
        }

        // Fermeture du Scanner pour libérer les ressources
        scanner.close();
    }
}