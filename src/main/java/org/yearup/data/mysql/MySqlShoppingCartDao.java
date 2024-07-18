package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.sql.*;
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

    @Override
    public Optional<ShoppingCart> postToCart(int userId, int id) {

        Optional<ShoppingCart> cart = getByUserId(userId);

        if (cart.isPresent() && cart.get().contains(id)) {
            //put
            int quantity = cart.get().getQuantity(id);
            String sqlPut = "UPDATE shopping_cart SET quantity = ? WHERE user_id = ? AND product_id = ?";
            try (Connection connection = getConnection()) {
                PreparedStatement putStatement = connection.prepareStatement(sqlPut);
                putStatement.setInt(1, quantity + 1);
                putStatement.setInt(2, userId);
                putStatement.setInt(3, id);

                putStatement.executeUpdate();

                cart = getByUserId(userId);

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            String sqlPost = "INSERT INTO shopping_cart(user_id, product_id, quantity) VALUES (?, ?, ?)";

            try (Connection connection = getConnection()) {
                PreparedStatement postStatement = connection.prepareStatement(sqlPost);
                postStatement.setInt(1, userId);
                postStatement.setInt(2, id);
                postStatement.setInt(3, 1);

                postStatement.executeUpdate();

                cart = getByUserId(userId);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

        return cart;

//        try (Connection connection = getConnection()) {
//            PreparedStatement checkStatment = connection.prepareStatement(checkSql);
//            checkStatment.setInt(1, userId);
//            checkStatment.setInt(2, id);
//
//            ResultSet row = checkStatment.executeQuery();
//
//
//
//            if (row.next()) {
//                var productQuantity = row.getInt("quantity");
//
//                statement.setInt(3, productQuantity + 1);
//            } else {
//                PreparedStatement statement = connection.prepareStatement(sql);
//                statement.setInt(1, userId);
//                statement.setInt(2, id);
//                statement.setInt(3, 1);
//            }
//
//            statement.executeUpdate();
//
//
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//        return null;
    }

    //load shopping cart
    //check if product in cart
    //if in cart update quantity
    //else insert with quantity = 1
    //return shopping cart

    @Override
    public Optional<ShoppingCart> deleteFromCart(int userId) {
        Optional<ShoppingCart> cart = getByUserId(userId);

        String sql = "DELETE FROM shopping_cart WHERE user_id = ?";

        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);

            statement.executeUpdate();
            cart = getByUserId(userId);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return cart;
    }

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
        Timestamp createdDate = row.getTimestamp("created_date");
        Timestamp lastModifiedDate = row.getTimestamp("last_modified_date");

         Product product = new Product(productId, name, price, categoryId, description, color, stock, isFeatured, imageUrl, createdDate, lastModifiedDate
        );

        return new ShoppingCartItem(product, quantity);
    }

}
