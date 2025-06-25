/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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

    public List<Specifications> getByOptionId(int optionId) {
        List<Specifications> list = new ArrayList<>();
        String sql = "SELECT s.SpecID, s.SpecName, ohs.Value "
                + "FROM OptionHasSpec ohs "
                + "JOIN Specifications s ON ohs.SpecID = s.SpecID "
                + "WHERE ohs.OptionID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, optionId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Specifications spec = new Specifications();
                spec.setSpecID(rs.getInt("SpecID"));
                spec.setSpecName(rs.getString("SpecName"));                
                list.add(spec);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
