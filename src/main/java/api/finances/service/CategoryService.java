package api.finances.service;


import api.finances.exception.customExceptions.NotFoundException;
import api.finances.model.Category;
import api.finances.model.User;
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

    public List<Category> getCategories(Long user_id) {

        User user = this.userService.findById(user_id);

        return user.getCategories();
    }

    public void create(Category category) {

        this.userService.findById(category.getUser().getId());

        this.categoryRepository.save(category);
    }

    public Category findByUserId(Long id, Long user_id) {
        return this.categoryRepository.findByUserId(id, user_id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public Category update(Long id, Long user_id, Category category) {

        this.findByUserId(id, user_id);

        this.userService.findById(category.getUser().getId());

        category.setId(id);

        return this.categoryRepository.save(category);
    }

    public void delete(Long id, Long user_id) {

        this.findByUserId(id, user_id);

        this.categoryRepository.deleteById(id);
    }

    public Category findById(Long id) {
        return this.categoryRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
}
