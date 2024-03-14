package api.finances.controller;


import api.finances.mapper.CategoryMapper;
import api.finances.model.Category;
import api.finances.model.dto.CategoryDto;
import api.finances.model.request.CategoryRequest;
import api.finances.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    @GetMapping("/user/{id}")
    public List<CategoryDto> getCategoriesByUserId(@PathVariable("id") Long user_id) {

        List<Category> categoryList = this.categoryService.getCategories(user_id);

        return this.categoryMapper.toListDto(categoryList);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@Valid @RequestBody CategoryRequest categoryRequest) {

        Category category = this.categoryMapper.toDomainModel(categoryRequest);

        this.categoryService.create(category);
    }

    @GetMapping("/{id}/user/{user_id}")
    public CategoryDto findOne(@PathVariable Long id, @PathVariable Long user_id) {

        Category category = this.categoryService.findByUserId(id, user_id);

        return this.categoryMapper.toDto(category);
    }

    @PutMapping("/{id}")
    public CategoryDto update(@PathVariable Long id,
                              @Valid @RequestBody CategoryRequest categoryRequest) {

        Long user_id = categoryRequest.getUser().getId();

        Category category = this.categoryMapper.toDomainModel(categoryRequest);

        this.categoryService.update(id, user_id, category);

        Category categoryUpdated = this.categoryService.findByUserId(id, user_id);

        return this.categoryMapper.toDto(categoryUpdated);
    }

    @DeleteMapping("/{id}/user/{user_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id, @PathVariable Long user_id) {
        this.categoryService.delete(id, user_id);
    }
}
