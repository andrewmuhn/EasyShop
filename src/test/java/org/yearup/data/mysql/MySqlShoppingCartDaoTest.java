package org.yearup.data.mysql;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MySqlShoppingCartDaoTest extends BaseDaoTestClass{

    private MySqlShoppingCartDao dao;

    private int testUserId;

    @BeforeEach
    public void setup() {
        testUserId = 1;
        dao = new MySqlShoppingCartDao(dataSource);
    }

    @AfterEach
    public void cleanup() {
        dao.deleteFromCart(testUserId);
    }

    @Test
    public void getCart_shouldContainProductIn_theUserCart() {
        dao.postToCart(testUserId, 1);
        dao.postToCart(testUserId, 1);

        var actual = dao.getByUserId(testUserId);
        System.out.println(actual.get().getQuantity(1));

        assertEquals(2, actual.get().getQuantity(1));
        assertTrue(actual.get().contains(1));
    }

    @Test
    public void getCart_shouldReturnFalseForNotAddedProduct() {
        dao.postToCart(testUserId, 1);

        var actual = dao.getByUserId(testUserId);

        assertFalse(actual.get().contains(2));
    }

    @Test
    public void postToCart_shouldIncrementQuantityIn_theUserCart() {
        dao.postToCart(testUserId, 3);
        dao.postToCart(testUserId, 3);
        dao.postToCart(testUserId, 3);

        var actual = dao.getByUserId(testUserId);

        assertEquals(3, actual.get().getQuantity(3));
    }

    @Test
    public void deleteFromCart_shouldDelete_theUserCart() {
        dao.postToCart(testUserId, 1);
        dao.postToCart(testUserId, 1);

        var beforeDelete = dao.getByUserId(testUserId);

        assertEquals(2, beforeDelete.get().getQuantity(1));

        dao.deleteFromCart(testUserId);

        var afterDelete = dao.getByUserId(testUserId);

        assertFalse(afterDelete.get().contains(1));
    }

}
