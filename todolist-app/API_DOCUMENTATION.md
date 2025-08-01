# Documentation API REST

## Authentification

L'accès à l'API nécessite le rôle `API_USER`. Utilisez l'authentification Basic HTTP avec :
- Username: `admin`
- Password: `admin`

## Endpoints

### Users

#### GET /api/users
Récupère tous les utilisateurs.

**Réponse :**
```json
[
  {
    "id": 1,
    "username": "jean_dupont",
    "email": "jean.dupont@example.com",
    "name": "Jean Dupont"
  }
]
```

#### GET /api/users/{id}
Récupère un utilisateur spécifique.

#### POST /api/users
Crée un nouvel utilisateur.

**Corps de la requête :**
```json
{
  "username": "nouveau_user",
  "email": "nouveau@example.com",
  "name": "Nouveau User"
}
```

#### PUT /api/users/{id}
Met à jour un utilisateur.

#### DELETE /api/users/{id}
Supprime un utilisateur.

### Tasks

#### GET /api/tasks
Récupère toutes les tâches.

#### GET /api/tasks/{id}
Récupère une tâche spécifique.

#### GET /api/tasks/user/{userId}
Récupère toutes les tâches d'un utilisateur.

#### POST /api/tasks
Crée une nouvelle tâche.

**Corps de la requête :**
```json
{
  "title": "Nouvelle tâche",
  "description": "Description de la tâche",
  "userId": 1,
  "priority": "HIGH",
  "status": "TODO"
}
```

#### PUT /api/tasks/{id}
Met à jour une tâche.

#### DELETE /api/tasks/{id}
Supprime une tâche.

## Exemples avec cURL

### Authentification et récupération des tâches
```bash
curl -u admin:admin http://localhost:8080/api/tasks
```

### Créer une nouvelle tâche
```bash
curl -u admin:admin -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Nouvelle tâche API",
    "description": "Créée via l'API",
    "userId": 1,
    "priority": "HIGH",
    "status": "TODO"
  }'
```