package api.finances.controller;


import api.finances.mapper.CategoryMapper;
import api.finances.model.Category;
import api.finances.model.dto.CategoryDto;
import api.finances.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public List<CategoryDto> listAll() {

        List<Category> categoryList = this.categoryService.findAll();

        return this.categoryMapper.toListDto(categoryList);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody Category category) {
        this.categoryService.create(category);
    }

    @GetMapping("/{id}")
    public CategoryDto findOne(@PathVariable Long id){

        Category category = this.categoryService.findById(id);

        return this.categoryMapper.toDto(category);
    }

    @PutMapping("/{id}")
    public CategoryDto update(@PathVariable Long id, @RequestBody Category category) {
        Category categoryUpdated = this.categoryService.update(id, category);

        return this.categoryMapper.toDto(categoryUpdated);
    }
}
