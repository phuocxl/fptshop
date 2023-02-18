package fa.training.repositories;

import fa.training.entites.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductNameContaining(String name);
    Page<Product> findByProductNameContaining(String name, Pageable pageable);

    List<Product> findByProductNameAndColor(String name, String color);

    @Query("select p from Product p where p.productName = :name ")
    List<Product> findByProductName(@Param("name") String name);


    public List<Product> findByProductName(String productName,Pageable pageable);

    @Query(value = "select top(4) * from product  where category_id = 1", nativeQuery = true)
    public List<Product> findProductApple();
}
