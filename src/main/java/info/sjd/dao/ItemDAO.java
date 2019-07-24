package info.sjd.dao;

import info.sjd.model.Item;
import info.sjd.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    public  static Item create(Item item){

        String sql = "INSERT INTO items(item_code, name, price) " +
                "VALUES(?,?,?)";

        try (Connection connection = PSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1, item.getItemCode());
            preparedStatement.setString(2, item.getName());
            preparedStatement.setInt(3, item.getPrice());

            preparedStatement.executeUpdate();

            Item createdItem = findByItemCode(item.getItemCode());
            if (createdItem != null){
                item.setId(createdItem.getId());
            } else{
                return null;
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return item;
    }

    public static Item findByItemCode(String itemCode) {
        Item item = null;

        String sql = "SELECT * FROM items WHERE id=?";

        try (
                Connection connection = PSQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){

            preparedStatement.setString(1, itemCode);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                item = new Item();
                setItemParams(item, resultSet);
                return item;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return item;
    }

    public static List<Item> findAll() {
        List<Item> items = new ArrayList<>();

        String sql = "SELECT * FROM items";

        try (
                Connection connection = PSQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Item item = new Item();
                setItemParams(item, resultSet);
                items.add(item);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return items;
    }

    public static Item findById(Integer id) {
        Item item = null;

        String sql = "SELECT * FROM item WHERE id=?";

        try (
                Connection connection = PSQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                item = new Item();
                setItemParams(item, resultSet);
                return item;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return item;
    }

    public static Item update(Item item) {
        Item updatedItem = null;

        String sql = "UPDATE items SET item_code=?, name=?, price=? WHERE id=?";

        try (
                Connection connection = PSQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){
            preparedStatement.setString(1, item.getItemCode());
            preparedStatement.setString(2, item.getName());
            preparedStatement.setInt(3, item.getPrice());
            preparedStatement.setInt(5, item.getId());

            preparedStatement.executeUpdate();

            updatedItem = new Item();
            updatedItem.clone(item);

        } catch (SQLException e){
            e.printStackTrace();
        }

        return updatedItem;
    }

    public static void delete(Integer id) {

        String sql = "DELETE FROM items WHERE id=?";

        try (
                Connection connection = PSQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static List<Item> findByUserIdAndOpenCart(Integer userId){
        List<Item> items = new ArrayList<>();

        String sql = "SELECT i.id, i.item_code, i.name, i.price FROM items i " +
                "JOIN orders o ON i.id=o.item_id " +
                "JOIN carts c ON o.cart_id=c.id " +
                "WHERE c.user_id=? AND c.closed=0";

        return items;
    }

    private static void setItemParams(Item item, ResultSet resultSet) {
        try {
            item.setItemCode(resultSet.getString("item_code"));
            item.setName(resultSet.getString("name"));
            item.setPrice(resultSet.getInt("price"));
            item.setId(resultSet.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
