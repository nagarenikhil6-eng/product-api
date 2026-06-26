package product_api.service;

import product_api.entity.Product;
import product_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts(String category, Long lastId, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        
        if (category != null && !category.isEmpty()) {
            return productRepository.findByCategoryWithCursor(category, lastId, pageable);
        } else {
            return productRepository.findWithCursor(lastId, pageable);
        }
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
public Product updateProduct(Long id, Product updatedProduct) {

    Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));

    product.setName(updatedProduct.getName());
    product.setCategory(updatedProduct.getCategory());
    product.setPrice(updatedProduct.getPrice());

    return productRepository.save(product);
}

}