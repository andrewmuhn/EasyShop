package org.yearup.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yearup.data.mysql.MySqlProductDao;
import org.yearup.exceptions.EntityNotFoundException;
import org.yearup.mapper.ProductMapper;
import org.yearup.models.Category;
import org.yearup.models.Product;
import org.yearup.models.dto.CreateProductDTO;
import org.yearup.models.dto.ProductDTO;
import org.yearup.models.dto.UpdateProductDTO;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {
    private MySqlProductDao productDao;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    public ProductService(MySqlProductDao productDao) {
        this.productDao = productDao;
    }

//    public List<ProductDTO> getProductsByCategoryId(int id) {
//        List<Product> products = this.productDao.listByCategoryId(id).orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found"));
//        ;
//        return products.stream().map(productMapper::toProductDTO).toList();
//    }

    public ProductDTO getProductById(int id) {

        var product = this.productDao.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found"));
        return productMapper.toProductDTO(product);
    }

    public List<ProductDTO> search(Integer categoryId, BigDecimal minPrice, BigDecimal maxPrice, String color) {
        List<Product> products = productDao.search(categoryId, minPrice, maxPrice, color);

        return products.stream().map(productMapper::toProductDTO).toList();
    }

    public ProductDTO createProduct(CreateProductDTO productDTO) {
        Product product = productMapper.fromCreateProductDTO(productDTO);
        Product createdProduct = productDao.create(product);
        return productMapper.toProductDTO(createdProduct);
    }

    public void updateProduct(UpdateProductDTO productDTO, int id) {
        Product product =  productDao.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found"));

        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setCategoryId(productDTO.getCategoryId());
        product.setDescription(productDTO.getDescription());
        product.setColor(productDTO.getColor());
        product.setStock(productDTO.getStock());
        product.setFeatured(productDTO.isFeatured());
        product.setImageUrl(productDTO.getImageUrl());

        productDao.update(id, product);
    }
}