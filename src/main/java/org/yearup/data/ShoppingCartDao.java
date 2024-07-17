package org.yearup.data;

import org.yearup.models.ShoppingCart;

import java.util.Optional;

public interface ShoppingCartDao
{
    Optional<ShoppingCart> getByUserId(int userId);
    // add additional method signatures here
}
