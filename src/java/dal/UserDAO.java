
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class UserDAO extends DBContext{
    public User getByUserID(String userID){
        String sql="sellect * from User where userID =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userID);
            ResultSet rs=st.executeQuery();
            if (rs.next()){
                User u = new User(rs.getInt("userID"), rs.getInt("roleID"), rs.getString("fullName"), rs.getString("email"), rs.getString("phoneNumber"), rs.getString("address"), rs.getString("gender"));
                return u;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
