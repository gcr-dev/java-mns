# Todo List Application

Application web de gestion de tâches développée avec Spring Boot, utilisant une base de données H2 et Thymeleaf pour les vues.

## Fonctionnalités

### Critères minimum implémentés ✅
- ✅ L'application démarre correctement
- ✅ Connexion à une base de données H2 (auto-configurée)
- ✅ Gestion de deux modèles : Users et Tasks avec relation Many-to-One
- ✅ Données initiales chargées automatiquement au démarrage
- ✅ Affichage des listes et détails :
  - `/` : Page d'accueil avec statistiques
  - `/users` : Liste de tous les utilisateurs
  - `/users/{id}` : Détail d'un utilisateur avec ses tâches
  - `/tasks` : Liste de toutes les tâches
  - `/tasks/{id}` : Détail d'une tâche

### Critères facultatifs implémentés ✅
- ✅ Bootstrap 5.3.0 pour le style CSS
- ✅ Fonctions CRUD complètes (ajout, modification, suppression)
- ✅ API REST complète avec endpoints pour Users et Tasks
- ✅ Spring Security avec authentification
- ✅ Interface entièrement en français
- ✅ Validation des données
- ✅ Messages de succès/erreur

## Structure du projet

```
todolist-app/
├── src/main/java/com/example/todolist/
│   ├── model/
│   │   ├── User.java
│   │   └── Task.java
│   ├── repository/
│   │   ├── UserRepository.java
│   │   └── TaskRepository.java
│   ├── controller/
│   │   ├── HomeController.java
│   │   ├── UserController.java
│   │   ├── TaskController.java
│   │   ├── LoginController.java
│   │   └── api/
│   │       ├── UserRestController.java
│   │       └── TaskRestController.java
│   ├── config/
│   │   ├── DataInitializer.java
│   │   └── SecurityConfig.java
│   └── TodolistApplication.java
├── src/main/resources/
│   ├── templates/
│   │   ├── index.html
│   │   ├── users/
│   │   │   ├── list.html
│   │   │   └── detail.html
│   │   ├── tasks/
│   │   │   ├── list.html
│   │   │   ├── detail.html
│   │   │   └── form.html
│   │   └── login.html
│   ├── application.properties
│   └── messages.properties
└── pom.xml
```

## Prérequis

- Java 17 ou supérieur
- Pas besoin d'installer Maven (utilise le wrapper intégré)

## Installation et lancement

1. Cloner ou télécharger le projet

2. Se placer dans le répertoire du projet :
```bash
cd todolist-app
```

3. Lancer l'application :
```bash
./mvnw spring-boot:run
```

4. Ouvrir un navigateur et aller à : http://localhost:8080

## Base de données H2

- Console H2 accessible à : http://localhost:8080/h2-console
- JDBC URL : `jdbc:h2:mem:testdb`
- Username : `sa`
- Password : (laisser vide)

## Authentification

### Utilisateurs par défaut
- **Utilisateur normal** : `user` / `password`
- **Administrateur** : `admin` / `admin` (accès API inclus)

## Données initiales

L'application charge automatiquement :
- 3 utilisateurs (Jean Dupont, Marie Martin, Pierre Bernard)
- 8 tâches en français réparties entre les utilisateurs
- Différents statuts (À faire, En cours, Terminé) et priorités (Faible, Moyenne, Élevée)

## API REST

L'application expose une API REST complète. Voir [API_DOCUMENTATION.md](API_DOCUMENTATION.md) pour les détails.

### Exemple d'utilisation
```bash
curl -u admin:admin http://localhost:8080/api/tasks
```

## Technologies utilisées

- Spring Boot 3.2.0
- Spring Data JPA
- Spring Security
- H2 Database (en mémoire)
- Thymeleaf + Spring Security integration
- Bootstrap 5.3.0
- Maven
- API REST