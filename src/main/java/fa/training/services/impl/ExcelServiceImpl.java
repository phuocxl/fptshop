package fa.training.services.impl;

import fa.training.entites.Product;
import fa.training.factory.excel.ExcelHelper;
import fa.training.repositories.ProductRepository;
import fa.training.services.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ByteArrayInputStream load() {
        List<Product> products = productRepository.findAll();

        ByteArrayInputStream in = ExcelHelper.productToExcel(products);
        return in;
    }

    @Override
    public void save(MultipartFile file) {
        try {
            List<Product> products = ExcelHelper.excelToProduct(file.getInputStream());
            productRepository.saveAll(products);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

}
