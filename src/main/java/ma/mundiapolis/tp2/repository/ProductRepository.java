package ma.mundiapolis.tp2.repository;

import java.util.List;

import ma.mundiapolis.tp2.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Recherche par mot-clé dans le nom (méthode dérivée Spring Data)
    List<Product> findByNameContainsIgnoreCase(String keyword);

    // Produits dont le prix dépasse une valeur
    List<Product> findByPriceGreaterThan(double price);

    // Même recherche par mot-clé, écrite en JPQL
    @Query("select p from Product p where lower(p.name) like lower(:kw)")
    List<Product> search(@Param("kw") String keyword);
}
