package fa.training.repository;

import fa.training.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductNameContaining(String name);
    Page<Product> findByProductNameContaining(String name, Pageable pageable);

    List<Product> findByProductNameAndColor(String name, String color);
    List<Product> findByProductName(String name);

    public List<Product> findByProductName(String productName,Pageable pageable);
}
