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
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


//@Controller
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryServices categoryServices;

    // RestAPI

    //get All
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

    @GetMapping("/apple")
    public ResponseEntity<DataRespose> getAllProductApple() {
        List<Product> product = productService.findProductApple();
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

    //getProductDTO
//    @GetMapping("/productDTO")
    public List<ProductDTO> getAll() {
        return productService.getAllProductDTO();
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


    @GetMapping("/pageable/{offset}/{limit}")
    public ResponseEntity<DataRespose> getPageable(@PathVariable("limit") int limit,
                                                   @PathVariable("offset") int offset) {
        Pageable pageable = PageRequest.of(offset, limit);
        Page<Product> productPage = productService.getPageProduct(pageable);
        if (productPage != null) {
            int totalPages = productPage.getTotalPages();
            return (offset + 1 > totalPages) ? ResponseEntity.status(HttpStatus.OK).body(
                    new DataRespose("error", "cannot page =" + offset)
            ) : ResponseEntity.status(HttpStatus.OK).body(
                    new DataRespose(productPage)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DataRespose("error", "cannot find product")
            );
        }
    }


    //Add product
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
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

    //Delete Product
    @DeleteMapping("/{id}")
    public boolean deleteProduct1(@PathVariable("id") Long id) {
        return productService.deleteProduct(id);
    }


    //Thymeleaf

    @GetMapping("/list")
    public String getAllProduct(Model model, @RequestParam(name = "name", required = false) String name) {

        List<Product> listSearch = null;

        if (StringUtils.hasText(name)) {
            listSearch = productService.findByProductNameContaining(name);
        } else {
            listSearch = productService.getProduct();
        }
        model.addAttribute("product", listSearch);
        return "index--";
    }

    @GetMapping("/paginated")
    //paginated
    public String getAllProduct(Model model, @RequestParam(name = "name", required = false) String name,
                                @RequestParam("page") Optional<Integer> page,
                                @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(0);
        int pageSize = size.orElse(5);

        Pageable pageable = PageRequest.of(currentPage, pageSize);
        //Sort.by("fullName")
        Page<Product> resultPage = null;


        if (StringUtils.hasText(name)) {
            resultPage = productService.findByProductNameContaining(name, pageable);
            model.addAttribute("name", name);
        } else {
            resultPage = productService.getPageProduct(pageable);
        }

        int totalPages = resultPage.getTotalPages();
        if (totalPages > 0) {
            int start = Math.max(1, currentPage - 2);
            int end = Math.min(currentPage + 2, totalPages);

            if (totalPages > 5) {
                if (end == totalPages) {
                    start = end - 5;
                } else if (start == 1) {
                    end = start + 5;
                }
            }

            List<Integer> pageNumber = IntStream.rangeClosed(start, end)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumber", pageNumber);

        }

        model.addAttribute("product", resultPage);
        return "index";
    }


    @GetMapping("/list/add")
    public ModelAndView viewAddProduct(Product product) {
        return new ModelAndView("addproduct");
    }

    @PostMapping("/list")
    public String saveProduct(@ModelAttribute("save") Product product) {
        productService.addProduct(product);
        return "redirect:/paginated";
    }

    @GetMapping("/list/update/{id}")
    public String viewUpdateProduct(@PathVariable("id") Long id, Model model) {
        Optional<Product> product = productService.getOneProduct(id);
        Product product1 = product.get();
        model.addAttribute("product", product1);
        return "update";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute("product") Product product) {
        productService.updateProduct(id, product);
        return "redirect:/paginated";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/paginated";
    }

}
