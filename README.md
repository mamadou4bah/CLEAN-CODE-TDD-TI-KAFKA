# Test SI PMU

# Docker est obligatoire pour utiliser l'application

## Lancement de l'application
- cd si-pmu-api/si-pmu-api
- mvn clean install -DskipTests

- cd ..

- docker-compose build

Dans le fichier main de l'application (SiPmuApiApplication.java) -> lancer l'application

Toutes les images et containers docker (kafka, mysql, si-pmu-app) vont se lancer ainsi que l'application
les tables de la base de données vont se créer.

# Base données:
- Utiliser un outil comme Sqlectron pour créer la base de données 
(voir image explicatif dans le dossier src/main/resources/sql)
# NB: Toutes les information de connexion à la BD se trouve dans le fichier application-dev.yml) 

# Tester l'application
- Lancer les test unitaire qui se trouvent dans le dossier src/main/test
Il y'a des tests unitaires sur le service ajout course, du kafka, de tests d'intégration

- Vous pouvez aussi Editer des configurations
(voir le fichier src/main/resources/sql/Lancement.PNG)

# Pour tester l'application il faut utiliser Postman
- URL http://localhost:8099/si-pmu/api/v1/courses avec le payload biensûr

Depuis que j'ai intégré Kafka, le swagger ne marche plus et je n'ai pas eu le temps de voir pourquoi.

#Lien swagger
http://localhost:8099/si-pmu/swagger-ui/index.html

# NB
Les informations sensibles comme par exemple username, password, etc... je voulais les mettre dans un fichier .env
mais je n'ai pas eu le temps





