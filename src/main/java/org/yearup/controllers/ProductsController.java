package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.models.Product;
import org.yearup.data.ProductDao;
import org.yearup.models.dto.CreateProductDTO;
import org.yearup.models.dto.ProductDTO;
import org.yearup.models.dto.UpdateProductDTO;
import org.yearup.services.ProductService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("products")
@CrossOrigin
public class ProductsController
{
    private final ProductService productService;
    private final ProductDao productDao;

    @Autowired
    public ProductsController(ProductDao productDao, ProductService productService)
    {
        this.productDao = productDao;
        this.productService = productService;
    }

    @GetMapping("")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<ProductDTO>> search(@RequestParam(name="cat", required = false) Integer categoryId,
                                @RequestParam(name="minPrice", required = false) BigDecimal minPrice,
                                @RequestParam(name="maxPrice", required = false) BigDecimal maxPrice,
                                @RequestParam(name="color", required = false) String color
                                )
    {
        List<ProductDTO> products = productService.search(categoryId, minPrice, maxPrice, color);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @GetMapping("{id}")
    @PreAuthorize("permitAll()")
    public ProductDTO getById(@PathVariable int id )
    {
            var product = productService.getProductById(id);

            return product;
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody CreateProductDTO productDTO)
    {
            return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateProduct(@PathVariable int id, @RequestBody UpdateProductDTO productDTO)
    {
       productService.updateProduct(productDTO, id);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteProduct(@PathVariable int id)
    {
        try
        {
            var product = productDao.getById(id);

            if(product == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);

            productDao.delete(id);
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }
}
