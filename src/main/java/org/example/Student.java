package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Student {
    private List<Long> maths;
    private List<Long> french;
    private List<Long> history;
    private Double mathAverage;
    private Double frenchAverage;
    private Double historyAverage;
    private Double totalAverage;
    private String name;

    public Student(String name) {
        this.name = name;
        this.maths = new ArrayList<>();
        this.french = new ArrayList<>();
        this.history = new ArrayList<>();
        
        // Initialiser chaque liste avec 10 notes aléatoires entre 0 et 20
        for (int i = 0; i < 10; i++) {
            maths.add((long) Math.floor(Math.random() * 21));
            french.add((long) Math.floor(Math.random() * 21));
            history.add((long) Math.floor(Math.random() * 21));
        }
    }

    // Getters et setters
    public List<Long> getMaths() { return maths; }
    public List<Long> getFrench() { return french; }
    public List<Long> getHistory() { return history; }
    public Double getMathAverage() { return mathAverage; }
    public void setMathAverage(Double mathAverage) { this.mathAverage = mathAverage; }
    public Double getFrenchAverage() { return frenchAverage; }
    public void setFrenchAverage(Double frenchAverage) { this.frenchAverage = frenchAverage; }
    public Double getHistoryAverage() { return historyAverage; }
    public void setHistoryAverage(Double historyAverage) { this.historyAverage = historyAverage; }
    public Double getTotalAverage() { return totalAverage; }
    public void setTotalAverage(Double totalAverage) { this.totalAverage = totalAverage; }
    public String getName() { return name; }

    public static void main(String[] args) {
        // Créer une dizaine d'étudiants
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Alice"));
        students.add(new Student("Bob"));
        students.add(new Student("Charlie"));
        students.add(new Student("David"));
        students.add(new Student("Emma"));
        students.add(new Student("Frank"));
        students.add(new Student("Grace"));
        students.add(new Student("Henry"));
        students.add(new Student("Iris"));
        students.add(new Student("Jack"));

        // Calculer les moyennes pour chaque étudiant avec forEach
        students.forEach(student -> {
            // Calculer la moyenne de maths
            double mathAvg = student.getMaths().stream()
                .mapToLong(Long::longValue)
                .average()
                .orElse(0.0);
            student.setMathAverage(mathAvg);

            // Calculer la moyenne de français
            double frenchAvg = student.getFrench().stream()
                .mapToLong(Long::longValue)
                .average()
                .orElse(0.0);
            student.setFrenchAverage(frenchAvg);

            // Calculer la moyenne d'histoire
            double historyAvg = student.getHistory().stream()
                .mapToLong(Long::longValue)
                .average()
                .orElse(0.0);
            student.setHistoryAverage(historyAvg);

            // Calculer la moyenne totale
            double totalAvg = (mathAvg + frenchAvg + historyAvg) / 3.0;
            student.setTotalAverage(totalAvg);

            System.out.println(student.getName() + " - Maths: " + String.format("%.2f", mathAvg) +
                ", Français: " + String.format("%.2f", frenchAvg) +
                ", Histoire: " + String.format("%.2f", historyAvg) +
                ", Moyenne totale: " + String.format("%.2f", totalAvg));
        });

        System.out.println("\n--- Vérifications avec streams ---");

        // Vérifier que personne n'a moins de 5 de moyenne
        boolean allAbove5 = students.stream()
            .allMatch(student -> student.getTotalAverage() >= 5);
        System.out.println("Tous les étudiants ont au moins 5 de moyenne: " + allAbove5);

        // Vérifier qu'au moins un étudiant a plus de 10
        boolean atLeastOneAbove10 = students.stream()
            .anyMatch(student -> student.getTotalAverage() > 10);
        System.out.println("Au moins un étudiant a plus de 10 de moyenne: " + atLeastOneAbove10);

        // Trouver l'étudiant avec la meilleure moyenne
        Student bestStudent = students.stream()
            .max(Comparator.comparing(Student::getTotalAverage))
            .orElse(null);
        if (bestStudent != null) {
            System.out.println("\nMeilleur étudiant: " + bestStudent.getName() +
                " avec une moyenne de " + String.format("%.2f", bestStudent.getTotalAverage()));
        }

        // Calculer la moyenne de la classe
        double classAverage = students.stream()
            .mapToDouble(Student::getTotalAverage)
            .average()
            .orElse(0.0);
        System.out.println("Moyenne de la classe: " + String.format("%.2f", classAverage));

        // Créer une Map avec prénom -> moyenne de maths
        Map<String, Double> mathAveragesByName = students.stream()
            .collect(Collectors.toMap(
                Student::getName,
                Student::getMathAverage
            ));

        System.out.println("\n--- Map des moyennes de maths ---");
        mathAveragesByName.forEach((name, avg) -> 
            System.out.println(name + ": " + String.format("%.2f", avg)));
    }
}