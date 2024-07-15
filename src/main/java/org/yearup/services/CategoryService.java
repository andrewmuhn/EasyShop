package org.yearup.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yearup.data.CategoryDao;
import org.yearup.data.mysql.MySqlCategoryDao;
import org.yearup.mapper.CategoryMapper;
import org.yearup.models.Category;
import org.yearup.models.dto.CategoryDTO;

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
}
