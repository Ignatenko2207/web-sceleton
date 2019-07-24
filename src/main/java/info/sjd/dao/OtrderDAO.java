package info.sjd.dao;

import info.sjd.model.Item;
import info.sjd.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OtrderDAO {

    public  static Order create(Order order){

        String sql = "INSERT INTO orders(item_id, amount, cart_id) " +
                "VALUES(?,?,?)";

        return null;
    }

    public static Order findByItemAndCart(Integer itemId, Integer cartId) {
        Order order = null;

        String sql = "SELECT * FROM items WHERE item_id=? AND cart_id=?";


        return order;
    }

    public static List<Order> findByCartId(Integer cartId) {
        List<Order> orders = new ArrayList<>();

        String sql = "SELECT * FROM orders WHERE cart_id=?";

        return orders;
    }

    public static List<Order> findAll() {
        List<Order> orders = new ArrayList<>();

        String sql = "SELECT * FROM orders";

        return orders;
    }

    public static Order findById(Integer id) {
        Order oreder = null;

        String sql = "SELECT * FROM orders WHERE id=?";


        return oreder;
    }

    public static Order update(Order order) {
        Order updatedOrder = null;

        String sql = "UPDATE orders SET item_id=?, amount=?, cart_id=? WHERE id=?";

        return updatedOrder;
    }

    public static void delete(Integer id) {

        String sql = "DELETE FROM orders WHERE id=?";

    }

    private static void setOrderParams(Order order, ResultSet resultSet) {

    }
}
