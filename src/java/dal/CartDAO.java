/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.List;
import model.Cart;
import model.CartItem;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Admin
 */
public class CartDAO extends DBContext {

    public List<Cart> getAllCart() {
        String sql = "select * from cart";
        List<Cart> cart = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Cart c = new Cart(rs.getInt(1), rs.getInt(2), rs.getString(3));
                cart.add(c);
            }
        } catch (SQLException e) {
        }
        return cart;
    }

    public List<CartItem> getAllCartItem() {
        String sql = "select * from cartitem";
        List<CartItem> cartItem = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CartItem ci = new CartItem(rs.getInt(1), rs.getInt(2),
                        rs.getInt(3), rs.getString(4));
                cartItem.add(ci);
            }
        } catch (SQLException e) {
        }
        return cartItem;
    }

    public Cart geCartByUserId(int userId) {
        String sql = "select * from cart where userid = ?";
        Cart cart = new Cart();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                cart = new Cart(rs.getInt(1), rs.getInt(2), rs.getString(3));
            }
        } catch (SQLException e) {
        }
        return cart;
    }

    public CartItem getCartItemByCartId(int cartId) {
        String sql = "select * from cartitem where cartid = ?";
        CartItem cartItem = new CartItem();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cartId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                cartItem = new CartItem(rs.getInt(1), rs.getInt(2),
                        rs.getInt(3), rs.getString(4));
            }
        } catch (SQLException e) {
        }
        return cartItem;
    }

    public void removeAll(int cartId, int userId) {
        try {
            String sql1 = "delete from cartitem where cartid = ?";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setInt(1, cartId);
            st1.executeUpdate();
            String sql2 = "delete from cart where userid = ?";
            PreparedStatement st2 = connection.prepareStatement(sql2);
            st2.setInt(1, userId);
            st2.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void add(int cartId, int optionId, int quantity, String addAt) {
        String sql = "";
        Timestamp timestamp = parseTimestamp(addAt);

        if (checkHaveItem(cartId, optionId) == null) {
            sql = """
                     insert into cartitem(cartid, optionid, quantity, addedat)
                     values(?,?,?,?)
                     """;
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setInt(1, cartId);
                st.setInt(2, optionId);
                st.setInt(3, quantity);
                st.setTimestamp(4, timestamp);
                st.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            sql = """
                     update cartitem
                     set quantity = quantity + ?, addedat = ?
                     where cartid = ? and optionid = ?
                     """;
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setInt(1, quantity);
                st.setTimestamp(2, timestamp);
                st.setInt(3, cartId);
                st.setInt(4, optionId);
                st.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
        }
    }

    public void removeOption(int cartId, int userId, int optionId) {
        String sql = """
                          delete from cartitem where optionid = ? and cartid = ?
                          """;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, optionId);
            st.setInt(2, cartId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public CartItem checkHaveItem(int cartid, int optionId) {
        String sql = "select * from cartitem where cartid = ? and  optionid = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cartid);
            st.setInt(2, optionId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                CartItem cartItem = new CartItem(rs.getInt(1), rs.getInt(2),
                        rs.getInt(3), rs.getString(4));
                return cartItem;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Timestamp parseTimestamp(String addAt) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime date = LocalDateTime.parse(addAt, formatter);
            return Timestamp.valueOf(date);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        CartDAO cd = new CartDAO();
        List<Cart> cart = cd.getAllCart();
        List<CartItem> cartItem = cd.getAllCartItem();
        System.out.println(cart.get(0).getCartId());
        System.out.println(cartItem.get(0).getCartId());
    }

}
