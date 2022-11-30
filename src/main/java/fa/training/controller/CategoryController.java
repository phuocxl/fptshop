package fa.training.controller;

import fa.training.entity.Category;
import fa.training.service.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryServices categoryServices;

    @GetMapping("/")
    public List<Category> getAllCategory() {
        return categoryServices.getAllCategory();
    }

    @PostMapping("/add")
    public Category addCategory(@RequestBody Category category) {
        return categoryServices.addCategory(category);
    }

    @DeleteMapping("/{id}")
    public boolean deleteCategory(@PathVariable("id") long id) {
        return  categoryServices.deleteCategory(id);
    }

}
