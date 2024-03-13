package api.finances.controller;


import api.finances.mapper.CategoryMapper;
import api.finances.model.Category;
import api.finances.model.dto.CategoryDto;
import api.finances.model.request.CategoryRequest;
import api.finances.service.CategoryService;
import jakarta.validation.Valid;
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
    public void create(@Valid @RequestBody CategoryRequest categoryRequest) {

        Category category = this.categoryMapper.toDomainModel(categoryRequest);

        this.categoryService.create(category);
    }

    @GetMapping("/{id}")
    public CategoryDto findOne(@PathVariable Long id){

        Category category = this.categoryService.findById(id);

        return this.categoryMapper.toDto(category);
    }

    @PutMapping("/{id}")
    public CategoryDto update(@PathVariable Long id, @Valid @RequestBody CategoryRequest categoryRequest) {

        Category category = this.categoryMapper.toDomainModel(categoryRequest);

        Category categoryUpdated = this.categoryService.update(id, category);

        return this.categoryMapper.toDto(categoryUpdated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id){
        this.categoryService.delete(id);
    }
}
