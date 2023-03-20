package fa.training.services;

import fa.training.dto.ProductDTO;
import fa.training.entites.Category;
import fa.training.entites.Product;
import fa.training.repositories.ProductRepository;
import fa.training.services.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;
    Product product;
    Category category;

    @BeforeEach
    void setup() {
        product = new Product();
        category = new Category();
        category.setCategoryName("APPLE");
        product.setId(1l);
        product.setCategory(category);
        product.setDesc("het hang");
        product.setPrice(10l);
        product.setColor("TIM");
    }

    @Test
    void addProductNullTest() {
        //given

        //when

        //then
        assertNull(productService.addProduct(null));
    }

    @Test
    void addProductSuccessTest() {
        //given
        assertNotNull(product);

        //when
        when(productRepository.save(product)).thenReturn(product);
        //then
        assertEquals(product, productService.addProduct(product));
    }


    @Test
    void updateProductNullTest() {
        //given

        //when

        //then
        assertNull(productService.updateProduct(null,null));
    }

    @Test
    void updateProductNullIdTest() {
        //given
        long id = 34;
        //when
        when(productRepository.getReferenceById(id)).thenReturn(null);
        //then
        assertNull(productService.updateProduct(id,product));
    }

    @Test
    void updateProductSuccessTest() {
        //given
        long id = 34;
        Product productFromDB = new Product();
        //when
        when(productRepository.getReferenceById(id)).thenReturn(productFromDB);
        when(productRepository.saveAndFlush(productFromDB)).thenReturn(productFromDB);
        //then
        assertEquals(productFromDB, productService.updateProduct(id,product));
    }

    @Test
    void getAllProductSuccess() {
        List<Product> productListFromDB = new ArrayList<>();

        when(productRepository.findAll()).thenReturn(productListFromDB);

        List<Product> actual = productService.getProduct();

        assertEquals(productListFromDB, actual);

    }

    @Test
    void getOneProductSuccess() {
        // Given
        long id = 1;

        Optional<Product> productOneFromDB = productRepository.findById(id);

        assertEquals(productOneFromDB, productService.getOneProduct(id));
    }

    @Test
    void findByProductNameContainingSuccess() {
        String name = "IP";

        List<Product> productListFromDB = productRepository.findByProductNameContaining(name);

        assertEquals(productListFromDB, productService.findByProductNameContaining(name));
    }

    @Test
    void pageSuccess() {
        Pageable pageable = null;

        Page<Product> productPage = productRepository.findAll(pageable);

        assertEquals(productPage, productService.getPageProduct(pageable));
    }

    @Test
    void findByPageProductSuccess() {
        String name = "IP";
        Pageable pageable = null;

        Page<Product> productsFromDB = productRepository.findByProductNameContaining(name,pageable);

        assertEquals(productsFromDB, productService.findByProductNameContaining(name, pageable));
    }

    @Test
    void findByProductNameSuccess() {
        String name = "IP";

        List<Product> productListFromDB = productRepository.findByProductName(name);

        assertEquals(productListFromDB, productService.findByProductName(name));

    }



//    @Test
    void getAllProductDTO() {
        // Given
        List<Product> productListFromDB = List.of(product);
        ProductDTO dto = new ProductDTO();
        List<ProductDTO> expected = List.of(dto);

        // When
        when(productRepository.findAll()).thenReturn(productListFromDB);

        // Then
        List<ProductDTO> actual = productService.getAllProductDTO();
        assertEquals(expected, actual);
    }

}
