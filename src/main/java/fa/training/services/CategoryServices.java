package fa.training.services;

import fa.training.entites.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryServices {
    Category addCategory(Category category);
    Category updateCategory(long id, Category category);
    boolean deleteCategory(long id);
    List<Category> getAllCategory();
    Optional<Category> getOneCategory(Long id);
}
