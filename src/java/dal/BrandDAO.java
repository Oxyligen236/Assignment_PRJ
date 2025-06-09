/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Brand;

public class BrandDAO extends DBContext {

    public List<Brand> getAll() {
        List<Brand> list = new ArrayList<>();
        String sql = "select * from Brand b left join BrandImage bi on b.BrandID = bi.BrandID";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Brand b = new Brand(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));
                list.add(b);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Brand getBrandById(int id) {
        String sql = "select * from Brand b left join BrandImage bi on b.BrandID = bi.BrandID where b.BrandID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Brand(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args) {
        BrandDAO b = new BrandDAO();
        List<Brand> list = b.getAll();
        System.out.println(list.get(0).getUlr());
        System.out.println(b.getBrandById(3));
    }
}
