# Activité Pratique N°2 – ORM JPA Hibernate Spring Data

Cours : Architecture JEE et Java avancé – Mundiapolis 2026

**Stack :** Spring Boot 3.4 · Java 17+ · Spring Data JPA · Hibernate · H2 · Spring Web · Lombok

## Objectif
Créer une application Spring Boot qui gère des produits avec JPA / Hibernate et Spring Data :
entité `Product`, unité de persistance, repository Spring Data et tests des opérations CRUD.

## Étape 1 – Création du projet
Projet généré avec **Spring Initializr** (Maven, Java 17) avec les dépendances :
Spring Data JPA, H2 Database, Spring Web et Lombok.

```
tp2-jpa
 ├── pom.xml
 └── src/main/java/ma/mundiapolis/tp2/Tp2JpaApplication.java
```

## Étape 2 – Entité JPA `Product`
L'entité `Product` est annotée `@Entity`. L'identifiant `id` est la clé primaire
(`@Id`) générée automatiquement (`@GeneratedValue(strategy = IDENTITY)`).
Lombok génère les getters/setters, les constructeurs, le builder et `toString()`.

| Attribut | Type |
|---|---|
| id | Long |
| name | String |
| price | double |
| quantity | int |

## Étape 3 – Configuration de l'unité de persistance
Dans `application.properties` :
- base **H2 en mémoire** `jdbc:h2:mem:products-db` ;
- console H2 activée sur `/h2-console` ;
- `spring.jpa.hibernate.ddl-auto=create` : Hibernate crée la table `product` au démarrage ;
- `spring.jpa.show-sql=true` : affiche les requêtes SQL générées.

La configuration MySQL est fournie en commentaire.

## Étape 4 – Interface `ProductRepository` (Spring Data)
`ProductRepository` hérite de `JpaRepository<Product, Long>` : Spring Data génère
l'implémentation (save, findAll, findById, deleteById…). Méthodes ajoutées :
- `findByNameContainsIgnoreCase(String)` : recherche par mot-clé (méthode dérivée du nom) ;
- `findByPriceGreaterThan(double)` : produits plus chers qu'un prix donné ;
- `search(String)` : même recherche écrite en **JPQL** avec `@Query`.

## Étape 5 – Test des opérations de gestion des produits
Un `CommandLineRunner` dans `Tp2JpaApplication` exécute au démarrage :
1. ajout de 4 produits ;
2. consultation de tous les produits ;
3. consultation d'un produit (`findById`) ;
4. recherche (par mot-clé, par prix, en JPQL) ;
5. mise à jour d'un produit ;
6. suppression d'un produit.

Un `RestController` permet aussi de voir les données : `/products`, `/products/{id}`,
`/products/search?keyword=`. Le test JUnit `ProductRepositoryTest` (`@DataJpaTest`)
vérifie les opérations CRUD.

## Lancer le projet
1. IntelliJ → File → Open → `tp2-jpa`
2. Lancer `Tp2JpaApplication`
3. http://localhost:8085/products — console H2 : http://localhost:8085/h2-console
   (JDBC URL `jdbc:h2:mem:products-db`, utilisateur `sa`, sans mot de passe)

