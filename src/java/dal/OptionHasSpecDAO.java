/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.OptionHasSpec;
import model.Specifications;

/**
 *
 * @author Admin
 */
public class OptionHasSpecDAO extends DBContext{
    public List<OptionHasSpec> getByOptionId(int optionId) {
        List<OptionHasSpec> list = new ArrayList<>();
        SpecificationsDAO specDao = new SpecificationsDAO();
        String sql = "SELECT * FROM OptionHasSpec WHERE OptionID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, optionId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OptionHasSpec op = new OptionHasSpec();
                op.setOptionID(optionId);
                op.setSpecID(rs.getInt("specID"));
                op.setSpecValue("specValue");
                Specifications sp = specDao.getBySpecID(rs.getInt("specID"));
                op.setSpec(sp);                
                list.add(op);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
}
