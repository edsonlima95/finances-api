package api.finances.mapper;

import api.finances.model.Category;
import api.finances.model.dto.CategoryDto;
import api.finances.model.request.CategoryRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    @Autowired
    private ModelMapper modelMapper;

    public CategoryDto toDto(Category category) {
        return this.modelMapper.map(category, CategoryDto.class);
    }

    public Category toModel(CategoryDto categoryDto) {
        return this.modelMapper.map(categoryDto, Category.class);
    }

    public Category toDomainModel(CategoryRequest categoryRequest) {
        return this.modelMapper.map(categoryRequest, Category.class);
    }

    public List<CategoryDto> toListDto(List<Category> categoryList) {
        return categoryList.stream().map(this::toDto).collect(Collectors.toList());
    }

}

