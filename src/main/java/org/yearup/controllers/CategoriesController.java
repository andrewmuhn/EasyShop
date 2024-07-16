package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;
import org.yearup.models.dto.CategoryDTO;
import org.yearup.models.dto.CreateCategoryDTO;
import org.yearup.models.dto.ProductDTO;
import org.yearup.models.dto.UpdateCategoryDTO;
import org.yearup.services.CategoryService;
import org.yearup.services.ProductService;

import javax.validation.Valid;
import java.util.List;

// add the annotations to make this a REST controller
// add the annotation to make this controller the endpoint for the following url
    // http://localhost:8080/categories
// add annotation to allow cross site origin requests

@RestController
@RequestMapping("/categories")
@CrossOrigin
public class CategoriesController
{
    private CategoryService categoryService;
    private ProductService productService;
    private ProductDao productDao;


    // create an Autowired controller to inject the categoryDao and ProductDao
    @Autowired
    public CategoriesController(CategoryService categoryService, ProductDao productDao) {
        this.categoryService = categoryService;
        this.productDao = productDao;
    }


    // add the appropriate annotation for a get action
    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<CategoryDTO>> getAll()
    {
        // find and return all categories
        var categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // add the appropriate annotation for a get action
    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<CategoryDTO> getById(@PathVariable int id)
    {
        // get the category by id
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }

    // add annotation to call this method for a POST action
    // add annotation to ensure that only an ADMIN can call this function
    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CategoryDTO> addCategory(@Valid @RequestBody CreateCategoryDTO dto)
    {
        // insert the category
        var category = categoryService.createCategory(dto);

        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    // add annotation to call this method for a PUT (update) action - the url path must include the categoryId
    // add annotation to ensure that only an ADMIN can call this function
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateCategory(@PathVariable int id, @Valid @RequestBody UpdateCategoryDTO dto)
    {
        // update the category by id
        categoryService.updateCategory(dto, id);
//        return new ResponseEntity<>(category, HttpStatus.OK);

    }


    // add annotation to call this method for a DELETE action - the url path must include the categoryId
    // add annotation to ensure that only an ADMIN can call this function
    public void deleteCategory(@PathVariable int id)
    {
        // delete the category by id
    }
}
