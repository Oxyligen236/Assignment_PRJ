/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Type;

/**
 *
 * @author Admin
 */
public class TypeDAO extends DBContext{
     //Doc tat ca bang ra
    public List<Type> getAll() {
        List<Type> list = new ArrayList<>();
        String sql = "select * from Type";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Type t = new Type(rs.getInt("TypeID"), rs.getString("TypeName"));
                list.add(t);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
     public Type getTypeById(int id) {
        String sql = "select * from TypeID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Type(rs.getInt("TypeID"), rs.getString("TypeName"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args) {
        TypeDAO t = new TypeDAO();
        List<Type> list = t.getAll();
        System.out.println(list.get(0).getTypeID());
    }
}
