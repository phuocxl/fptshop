package fa.training.service;

import fa.training.entity.Category;
import fa.training.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServicesImpl implements CategoryServices {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        if(category != null) {
            return categoryRepository.save(category);
        }
        return null;
    }

    @Override
    public Category updateCategory(long id, Category category) {
        if(category != null) {
            Category category1 = categoryRepository.getReferenceById(id);
            if(category1 != null) {
                category1.setCategoryName(category.getCategoryName());
                return categoryRepository.saveAndFlush(category1);
            }
        }
        return null;
    }

    @Override
    public boolean deleteCategory(long id) {
        if(categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getOneCategory(Long id) {
        return categoryRepository.findById(id);
    }
}
