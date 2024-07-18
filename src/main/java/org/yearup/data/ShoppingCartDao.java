package org.yearup.data;

import org.yearup.models.ShoppingCart;

import java.util.Optional;

public interface ShoppingCartDao
{
    Optional<ShoppingCart> getByUserId(int userId);

    Optional<ShoppingCart> postToCart(int userId, int id);

    Optional<ShoppingCart> deleteFromCart(int userId);

    // add additional method signatures here
}
