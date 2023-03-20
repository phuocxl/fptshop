package fa.training.controllers;

import fa.training.dto.ProductDTO;
import fa.training.entites.Product;
import fa.training.model.DataRespose;
import fa.training.services.CategoryServices;
import fa.training.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryServices categoryServices;
    @GetMapping("/")
    public ResponseEntity<DataRespose> getAllProduct() {
        List<Product> product = productService.getProduct();
        if (product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataRespose("error", "cannot find product")
            );
        } else {

            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataRespose(product)
            );
        }
    }
    @GetMapping("/category")
    public ResponseEntity<DataRespose> getAllProductApple(@RequestParam(name = "id") long id) {
        List<Product> product = productService.findProductCategory(id);
        if (product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataRespose("error", "cannot find product")
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataRespose(product)
            );
        }

    }
    @GetMapping("/name")
    public ResponseEntity<DataRespose> getProductName(@RequestParam(name = "name") String name) {
        List<Product> productListName = productService.findByProductName(name);
        if (productListName.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataRespose("error","cannot find product")
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataRespose(productListName)
            );
        }
    }
    @GetMapping("/productDTO")
    public ResponseEntity<List<ProductDTO>> getAllProductDTO() {
        List<ProductDTO> product = productService.getAllProductDTO();
        if(product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<DataRespose> getOneProduct(@PathVariable("id") Long id) {
        Optional<Product> product = productService.getOneProduct(id);
        if (product.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataRespose(product)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DataRespose("error", "cannot find product id=" + id)
            );
        }
    }

    @GetMapping("/page")
    public ResponseEntity<Map<String, Object>> getProductPage(@RequestParam("offset") int offset,
                                                              @RequestParam("limit") int limit,
                                                              @RequestParam( required = false)
                                                                  String productName) {
        try {
            List<Product> products = new ArrayList<>();
            Pageable pageable = PageRequest.of(offset,limit, Sort.by("pricesSale").descending());
            Page<Product> productPage ;
            if(productName == null) {
                productPage = productService.getPageProduct(pageable);

            } else {
                productPage = productService.findByProductNameContaining(productName,pageable);

            }
            products = productPage.getContent();
            if(products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("product", products);
                response.put("currentPage",productPage.getNumber());
                response.put("totalItem",productPage.getTotalElements());
                response.put("totalPage",productPage.getTotalPages());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DataRespose> addProduct2(@RequestBody Product product) {

        List<Product> productName = productService.findByProductName(product.getProductName().trim());

        if (productName.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataRespose("", "product name already taken")
            );
        } else {
            product = new Product(product.getProductName(), product.getDesc(), product.getColor(),
                    product.getPrice(),product.getPricesSale(),product.getImage(), product.getCategory());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataRespose("", "add success", productService.addProduct(product))
            );
        }

    }

    //Update Product
    @PutMapping("/{id}")
    public ResponseEntity<DataRespose> updateProduct1(@PathVariable("id") Long id, @RequestBody Product product) {
        Optional<Product> product1 = productService.getOneProduct(id);
        if (product1.isPresent()) {
            List<Product> productName = productService.findByProductName(product.getProductName().trim());
            if (productName.size() > 0) {
                return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                        new DataRespose("", "product name already taken")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataRespose("", "update success", productService.updateProduct(id, product))
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataRespose("", "cannot product id=" + id)
            );

        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DataRespose> delete(@PathVariable("id") long id) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataRespose("","delete success",productService.delete(id))
            );
    }
}
