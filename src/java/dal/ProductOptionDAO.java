/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ProductOption;

/**
 *
 * @author Admin
 */
public class ProductOptionDAO extends DBContext {

    public List<ProductOption> getByProductID(int productId) {
        List<ProductOption> list = new ArrayList<>();
        String sql = "SELECT * FROM ProductOption WHERE ProductID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ProductOption option = new ProductOption();
                option.setOptionID(rs.getInt("optionID"));
                option.setProductID(productId);
                option.setQuantity(rs.getInt("quantity"));
                option.setOptionName(rs.getString("optionName"));
                option.setAdditionalPrice(rs.getDouble("additionalPrice"));
                list.add(option);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public ProductOption getByOptionId(int optionId) {
        String sql = "SELECT * FROM ProductOption WHERE OptionID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, optionId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                ProductOption option = new ProductOption();
                option.setOptionID(rs.getInt("OptionID"));
                option.setProductID(rs.getInt("ProductID"));
                option.setOptionName(rs.getString("OptionName"));
                option.setAdditionalPrice(rs.getDouble("AdditionalPrice"));
                return option;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
