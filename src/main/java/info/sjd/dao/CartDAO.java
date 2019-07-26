package info.sjd.dao;

import info.sjd.model.Cart;
import info.sjd.model.Item;
import info.sjd.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {

    public  static Cart create(Cart cart){

        // closed must be always false
        cart.setClosed(false);

        String sql = "INSERT INTO carts(user_id, create_time, closed) " +
                "VALUES(?,?,?)";

        return null;
    }

    public static Cart findOpenCartByUserId(Integer userId) {
        Cart cart = null;

        String sql = "SELECT * FROM carts WHERE user_id=? AND closed=0";


        return cart;
    }

    public static List<Cart> findClosedByUserId(Integer userId) {
        List<Cart> orders = new ArrayList<>();

        String sql = "SELECT * FROM carts WHERE user_id=? AND closed=1";

        return orders;
    }

    public static List<Cart> findByUserId(Integer userId) {
        List<Cart> orders = new ArrayList<>();

        String sql = "SELECT * FROM carts WHERE user_id=?";

        return orders;
    }

    public static List<Cart> findAll() {
        List<Cart> carts = new ArrayList<>();

        String sql = "SELECT * FROM carts";

        return carts;
    }

    public static Cart findById(Integer id) {
        Cart cart = null;

        String sql = "SELECT * FROM carts WHERE id=?";


        return cart;
    }

    public static Cart update(Cart cart) {
        Cart updatedCart = null;

        String sql = "UPDATE carts SET user_id=?, create_time=?, closed=? WHERE id=?";

        return updatedCart;
    }

    public static void delete(Integer id) {

        String sql = "DELETE FROM carts WHERE id=?";

    }

    private static Integer getSumByPeriodAndUserId(Long from, Long to, Integer userId){
        Integer result = null;
        String sql = "";

        return result;
    }

    private static Integer getSumByOpenAndUserIdAndPeriod(Long from, Long to, Integer userId){
        Integer result = null;
        String sql = "";

        return result;
    }

    private static void setCartParams(Cart cart, ResultSet resultSet) {

    }
}
