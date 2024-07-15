package org.yearup.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yearup.data.mysql.MySqlCategoryDao;
import org.yearup.mapper.CategoryMapper;
import org.yearup.models.Category;
import org.yearup.models.dto.CategoryDTO;
import org.yearup.models.dto.CreateCategoryDTO;
import org.yearup.models.dto.UpdateCategoryDTO;

import java.util.List;

@Service
public class CategoryService {
    private MySqlCategoryDao categoryDao;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(MySqlCategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = this.categoryDao.getAllCategories();
        return categories.stream().map(categoryMapper::toCategoryDTO).toList();
    }

    public CategoryDTO getCategoryById(int id){
        Category category = categoryDao.getById(id);
//                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        return categoryMapper.toCategoryDTO(category);
    }

    public void deleteCategory(int id){
        Category category = categoryDao.getById(id);
//                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        categoryDao.delete(id);
    }

    public CategoryDTO createCategory(CreateCategoryDTO dto){
        Category category = categoryMapper.fromCreateCategoryDTO(dto);
        Category createdCategory = categoryDao.create(category);
        return categoryMapper.toCategoryDTO(createdCategory);
    }

    public void updateCategory(UpdateCategoryDTO dto, int id){
        Category category = categoryDao.getById(id);
//                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        category.setName(dto.getName());
        category.setDescription(dto.getDescription());

        categoryDao.update(id, category);
    };

}
