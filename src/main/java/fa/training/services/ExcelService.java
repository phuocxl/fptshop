package fa.training.services;

import fa.training.entites.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ExcelService {
    ByteArrayInputStream load();
    void save(MultipartFile file);

    public List<Product> getAllProducts();
}
