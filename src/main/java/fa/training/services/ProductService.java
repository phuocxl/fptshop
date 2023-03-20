package fa.training.services;

import fa.training.dto.ProductDTO;
import fa.training.entites.Category;
import fa.training.entites.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product addProduct(Product product);
    Product updateProduct(Long id, Product product);
    List<Product> getProduct();
    Optional<Product> getOneProduct(Long id);
    List<Product> findByProductNameContaining(String name);
    Page<Product> getPageProduct(Pageable pageable);
    boolean delete(long id);

    Page<Product> findByProductNameContaining(String name, Pageable pageable);

    List<Product> findByProductName(String name);

    public List<ProductDTO> getAllProductDTO();

    List<Product> findProductCategory( long id);
}
