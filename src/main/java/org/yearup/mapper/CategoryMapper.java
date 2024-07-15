package org.yearup.mapper;

import org.mapstruct.Mapper;
import org.yearup.models.Category;
import org.yearup.models.dto.CategoryDTO;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toCategoryDTO(Category category);
    Category toCategory(CategoryDTO categoryDTO);
}
