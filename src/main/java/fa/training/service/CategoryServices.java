package fa.training.service;

import fa.training.dto.ProductDTO;
import fa.training.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryServices {
    public Category addCategory(Category category);
    public Category updateCategory(long id, Category category);
    public boolean deleteCategory(long id);
    public List<Category> getAllCategory();
    public Optional<Category> getOneCategory(Long id);
}
