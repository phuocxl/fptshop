package fa.training.service;

import fa.training.dto.ProductDTO;
import fa.training.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public Product addProduct(Product product);
    public Product updateProduct(Long id, Product product);
    public boolean deleteProduct(Long id);
    public List<Product> getProduct();
    public Optional<Product> getOneProduct(Long id);
    List<Product> findByProductNameContaining(String name);
    public Page<Product> getPageProduct(Pageable pageable);

    Page<Product> findByProductNameContaining(String name, Pageable pageable);

    List<Product> findByProductName(String name);

    List<Product> findByProductNameAndColor(String name, String color);

    public List<ProductDTO> getAllProductDTO();
}
