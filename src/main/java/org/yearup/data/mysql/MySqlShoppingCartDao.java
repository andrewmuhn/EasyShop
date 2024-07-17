package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao {

    public MySqlShoppingCartDao(DataSource dataSource) {super(dataSource);}

    @Override
    public Optional<ShoppingCart> getByUserId(int userId) {
        ShoppingCart cart = new ShoppingCart();

        String sql = "SELECT * FROM shopping_cart " +
                "JOIN products ON shopping_cart.product_id = products.product_id " +
                "WHERE user_id = ?";

        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);

            ResultSet row = statement.executeQuery();

            while (row.next())
            {
                var item = mapRow(row);
                cart.add(item);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return Optional.of(cart);
    }

//    public Optional<ShoppingCartItem> getCartItems()

    private ShoppingCartItem mapRow(ResultSet row) throws SQLException{
        int productId = row.getInt("product_id");
        int quantity = row.getInt("quantity");

        String name = row.getString("name");
        BigDecimal price = row.getBigDecimal("price");
        int categoryId = row.getInt("category_id");
        String description = row.getString("description");
        String color = row.getString("color");
        int stock = row.getInt("stock");
        String imageUrl = row.getString("image_url");
        boolean isFeatured = row.getBoolean("featured");

         Product product = new Product(productId, name, price, categoryId, description, color, stock, isFeatured, imageUrl
        );

        return new ShoppingCartItem(product, quantity);
    }

}
