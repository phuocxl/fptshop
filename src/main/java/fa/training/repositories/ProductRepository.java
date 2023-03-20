package fa.training.repositories;
import fa.training.entites.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductNameContaining(String name);
    Page<Product> findByProductNameContaining(String name, Pageable pageable);
    @Query("select p from Product p where p.productName like concat('%',:name,'%')")
    List<Product> findByProductName(@Param("name") String name);
    @Query("select p from Product p where category.categoryId = :id")
    public List<Product> findProductCategory(@Param("id") long id);
}
