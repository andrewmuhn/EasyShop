//package org.yearup.controllers;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Import;
//import org.yearup.configuration.TestSecurityConfig;
//import org.yearup.data.mysql.MySqlShoppingCartDao;
//import org.yearup.models.ShoppingCart;
//
//import java.util.Optional;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(ShoppingCartController.class)
//@ExtendWith(MockitoExtension.class)
//@Import(TestSecurityConfig.class)
//public class ShoppingCartControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private MySqlShoppingCartDao shoppingCartDao;
//
//    @Test
//    public void testGetShoppingCart() throws Exception {
//        ShoppingCart cart = new ShoppingCart(3);
//
//        when(shoppingCartDao.getByUserId(3)).thenReturn(Optional.of(cart));
//
//        mockMvc.perform(get("/cart"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.userId").value(3));
//
//    }
//
//}
