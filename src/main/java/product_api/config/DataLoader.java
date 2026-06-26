package product_api.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import product_api.entity.Product;
import product_api.repository.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadProducts(ProductRepository repository) {

        return args -> {

            // Sirf tab insert kare jab database almost empty ho
            if (repository.count() > 10) {
                System.out.println("Products already exist.");
                return;
            }

            String[] categories = {
                    "Electronics",
                    "Fashion",
                    "Books",
                    "Sports",
                    "Grocery",
                    "Home",
                    "Beauty"
            };

            Random random = new Random();

            List<Product> batch = new ArrayList<>();

            for (int i = 1; i <= 200000; i++) {

                Product product = new Product();

                product.setName("Product " + i);
                product.setCategory(categories[random.nextInt(categories.length)]);
                product.setPrice(BigDecimal.valueOf(100 + random.nextInt(90000)));

                LocalDateTime now = LocalDateTime.now();
                product.setCreatedAt(now.minusSeconds(i)); // newest-first ordering
                product.setUpdatedAt(now.minusSeconds(i));

                batch.add(product);

                if (batch.size() == 1000) {
                    repository.saveAll(batch);
                    batch.clear();
                    System.out.println(i + " products inserted...");
                }
            }

            if (!batch.isEmpty()) {
                repository.saveAll(batch);
            }

            System.out.println("✅ 200000 Products Inserted Successfully");
        };
    }
}