package dal;

import java.util.ArrayList;
import java.util.List;
import model.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO extends DBContext {

    public List<Account> getAllAccount() {
        List<Account> accountList = new ArrayList<>();
        String sql = "select * from account";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account acc = new Account(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4));
                accountList.add(acc);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return accountList;
    }

    public void addAccount() {
        String sql = "";
        try {

        } catch (Exception e) {
        }
    }

    public List<Account> getAccountByUserId() {
        return null;
    }
    
    public Account checkAccount(String username, String password) {
        String sql = "select * from account where username = ? and passwordHash = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Account(rs.getInt(1), username, password, rs.getInt(4));
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public static void main(String[] args) {
        AccountDAO ad = new AccountDAO();
        List<Account> l = ad.getAllAccount();
        System.out.println(l.get(0).getUsername());
    }
}
