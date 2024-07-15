package org.yearup.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yearup.data.mysql.MySqlProductDao;
import org.yearup.mapper.ProductMapper;
import org.yearup.models.Product;
import org.yearup.models.dto.ProductDTO;

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

    public List<ProductDTO> getProductsByCategoryId(int id) {
        List<Product> products = this.productDao.listByCategoryId(id);
        return products.stream().map(productMapper::toProductDTO).toList();
    }
}
