package api.finances.service;


import api.finances.exception.customExceptions.NotFoundException;
import api.finances.model.Category;
import api.finances.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserService userService;

    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    public void create(Category category) {

        this.userService.findById(category.getUser().getId());

        this.categoryRepository.save(category);
    }

    public Category findById(Long id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public Category update(Long id, Category category) {

        this.findById(id);

        this.userService.findById(category.getUser().getId());

        category.setId(id);

        return this.categoryRepository.save(category);
    }

    public void delete(Long id) {

        this.findById(id);

        this.categoryRepository.deleteById(id);
    }
}
