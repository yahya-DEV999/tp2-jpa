package ma.mundiapolis.tp2;

import java.util.List;

import ma.mundiapolis.tp2.entities.Product;
import ma.mundiapolis.tp2.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Tp2JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(Tp2JpaApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository) {
        return args -> {
            // 1. Ajouter des produits
            productRepository.save(new Product(null, "Computer", 4500, 10));
            productRepository.save(new Product(null, "Printer", 1200, 4));
            productRepository.save(new Product(null, "Smartphone", 3200, 25));
            productRepository.save(Product.builder().name("Smart Watch").price(900).quantity(7).build());

            // 2. Consulter tous les produits
            System.out.println("===== Tous les produits =====");
            productRepository.findAll().forEach(System.out::println);

            // 3. Consulter un produit
            System.out.println("===== Produit id=1 =====");
            Product product = productRepository.findById(1L).orElseThrow();
            System.out.println(product);

            // 4. Chercher des produits
            System.out.println("===== Produits contenant 'smart' =====");
            List<Product> found = productRepository.findByNameContainsIgnoreCase("smart");
            found.forEach(System.out::println);

            System.out.println("===== Produits avec prix > 2000 =====");
            productRepository.findByPriceGreaterThan(2000).forEach(System.out::println);

            System.out.println("===== Recherche JPQL '%er%' =====");
            productRepository.search("%er%").forEach(System.out::println);

            // 5. Mettre à jour un produit
            product.setPrice(4200);
            product.setQuantity(8);
            productRepository.save(product);
            System.out.println("===== Après mise à jour =====");
            System.out.println(productRepository.findById(1L).orElseThrow());

            // 6. Supprimer un produit
            productRepository.deleteById(2L);
            System.out.println("===== Après suppression du produit id=2 =====");
            productRepository.findAll().forEach(System.out::println);
        };
    }
}
