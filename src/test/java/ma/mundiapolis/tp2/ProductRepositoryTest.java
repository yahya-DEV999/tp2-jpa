package ma.mundiapolis.tp2;

import static org.assertj.core.api.Assertions.assertThat;

import ma.mundiapolis.tp2.entities.Product;
import ma.mundiapolis.tp2.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void crudOperations() {
        Product saved = productRepository.save(new Product(null, "Keyboard", 250, 30));
        assertThat(saved.getId()).isNotNull();

        assertThat(productRepository.findById(saved.getId())).isPresent();
        assertThat(productRepository.findByNameContainsIgnoreCase("key")).hasSize(1);

        saved.setPrice(199);
        productRepository.save(saved);
        assertThat(productRepository.findById(saved.getId()).orElseThrow().getPrice()).isEqualTo(199);

        productRepository.deleteById(saved.getId());
        assertThat(productRepository.findById(saved.getId())).isEmpty();
    }
}
