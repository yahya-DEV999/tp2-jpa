package ma.mundiapolis.tp2.web;

import java.util.List;

import ma.mundiapolis.tp2.entities.Product;
import ma.mundiapolis.tp2.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Petit contrôleur REST pour vérifier les données dans le navigateur
@RestController
public class ProductRestController {

    private final ProductRepository productRepository;

    public ProductRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<Product> products() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public Product product(@PathVariable Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @GetMapping("/products/search")
    public List<Product> search(@RequestParam(defaultValue = "") String keyword) {
        return productRepository.findByNameContainsIgnoreCase(keyword);
    }
}
