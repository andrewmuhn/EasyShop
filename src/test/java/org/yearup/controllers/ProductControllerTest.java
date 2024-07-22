package org.yearup.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.yearup.configuration.TestSecurityConfig;
import org.yearup.data.ProductDao;
import org.yearup.data.mysql.MySqlProductDao;
import org.yearup.models.Product;
import org.yearup.models.dto.ProductDTO;
import org.yearup.services.ProductService;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductsController.class)
@ExtendWith(MockitoExtension.class)
@Import(TestSecurityConfig.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;
    @MockBean
    private ProductDao productDao;

    @Test
    public void testGetProductById() throws Exception {
        ProductDTO productDTO = new ProductDTO(1, "Smartphone", new BigDecimal("499.99"), 1,
                "A powerful and feature-rich smartphone for all your communication needs.",
                "Black", 50, false, "smartphone.jpg");

        when(productService.getProductById(1)).thenReturn(productDTO);

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(1));
    }
}