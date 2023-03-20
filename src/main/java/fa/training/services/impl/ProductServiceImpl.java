package fa.training.services.impl;

import fa.training.dto.ProductDTO;
import fa.training.entites.Category;
import fa.training.entites.Product;
import fa.training.repositories.ProductRepository;
import fa.training.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product addProduct(Product product) {
        if(product != null) {
            return productRepository.save(product);
        }
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        if(product != null) {
            Product product1 = productRepository.getById(id);
            if(product1 != null) {
                product1.setProductName(product.getProductName());
                product1.setCategory(product.getCategory());
                product1.setDesc(product.getDesc());
                product1.setColor(product.getColor());
                product1.setPrice(product.getPrice());
                return productRepository.saveAndFlush(product1);
            }
        }
        return null;
    }

    @Override
    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getOneProduct(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findByProductNameContaining(String name) {
        return productRepository.findByProductNameContaining(name);
    }

    @Override
    public Page<Product> getPageProduct(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public boolean delete(long id) {
        if(id > 0) {
            Product product = productRepository.getReferenceById(id);
            if(product != null) {
                productRepository.delete(product);
                return true;
            }
        }
        return false;
    }

    @Override
    public Page<Product> findByProductNameContaining(String name, Pageable pageable) {
        return productRepository.findByProductNameContaining(name,pageable);
    }

    @Override
    public List<Product> findByProductName(String name) {
        return productRepository.findByProductName(name);
    }
    @Override
    public List<ProductDTO> getAllProductDTO() {

        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product dto: products) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(dto.getId());
            productDTO.setProductName(dto.getProductName());
            productDTO.setCategoryName(dto.getCategory().getCategoryName());
            productDTO.setDesc(dto.getDesc());
            productDTO.setPrice(dto.getPrice());
            productDTO.setColor(dto.getColor());
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }

    @Override
    public List<Product> findProductCategory(long id) {
        return productRepository.findProductCategory( id);
    }

}
