/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import model.Specifications;

/**
 *
 * @author Admin
 */
public class SpecificationsDAO extends DBContext{

    public Specifications getBySpecID(int id) {
        String sql = "SELECT * FROM Specifications WHERE SpecID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Specifications(rs.getInt("SpecID"), rs.getString("SpecName"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
