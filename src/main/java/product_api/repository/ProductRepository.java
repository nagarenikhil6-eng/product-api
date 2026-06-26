package product_api.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import product_api.entity.Product;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.category = :category AND (:lastId IS NULL OR p.id < :lastId) ORDER BY p.id DESC")
    List<Product> findByCategoryWithCursor(
        @Param("category") String category, 
        @Param("lastId") Long lastId, 
        Pageable pageable
    );

    @Query("SELECT p FROM Product p WHERE :lastId IS NULL OR p.id < :lastId ORDER BY p.id DESC")
    List<Product> findWithCursor(
        @Param("lastId") Long lastId, 
        Pageable pageable
    );
}