package dal;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

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
                int accountId = rs.getInt("accountId");
                String uname = rs.getString("username");
                String pass = rs.getString("passwordHash");
                int userId = rs.getInt("userID");
                UserDAO userDAO = new UserDAO();
                User u = userDAO.getByUserID(userId);
                return new Account(accountId, uname,pass, u.getUserID());
            }
//            ResultSet rs = st.executeQuery();
//            if (rs.next()) {
//                return new Account(rs.getInt(1), username, password, rs.getInt(4));
//            }
        } catch (SQLException e) {
        }
        return null;
    }

    public boolean isEmailExists(String email) {
        String sql = "SELECT 1 FROM [User] WHERE Email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean isPhoneExists(String phone) {
        String sql = "SELECT 1 FROM [User] WHERE PhoneNumber = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean isUsernameExists(String username) {
        String sql = "SELECT 1 FROM Account WHERE Username = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public void registerAccount(String email, String username, String password,
            String fullname, String gender, String phone, String address) {
        // Câu lệnh thêm vào bảng User
        String insertUser = "INSERT INTO [User] (RoleID, FullName, Gender, PhoneNumber, Email, Address) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        // Câu lệnh thêm vào bảng Account
        String insertAccount = "INSERT INTO Account (Username, PasswordHash, UserID) VALUES (?, ?, ?)";

        try {
            connection.setAutoCommit(false); // Bắt đầu transaction

            // Thêm người dùng vào bảng User
            PreparedStatement ps1 = connection.prepareStatement(insertUser, PreparedStatement.RETURN_GENERATED_KEYS);
            ps1.setInt(1, 2); // RoleID = 2: mặc định là người dùng
            ps1.setString(2, fullname);
            ps1.setString(3, gender);
            ps1.setString(4, phone);
            ps1.setString(5, email);
            ps1.setString(6, address);
            ps1.executeUpdate();

            // Lấy UserID mới được tạo
            ResultSet rs = ps1.getGeneratedKeys();
            int userId = 0;
            if (rs.next()) {
                userId = rs.getInt(1);
            }

            // Thêm tài khoản vào bảng Account
            PreparedStatement ps2 = connection.prepareStatement(insertAccount);
            ps2.setString(1, username);
            ps2.setString(2, password); // nếu muốn hash thì dùng SHA256 tại đây
            ps2.setInt(3, userId);
            ps2.executeUpdate();

            connection.commit(); // Thành công, commit lại DB
        } catch (Exception e) {
            try {
                connection.rollback(); // Lỗi thì rollback
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true); // Reset lại
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        AccountDAO ad = new AccountDAO();
        List<Account> l = ad.getAllAccount();
        System.out.println(l.get(0).getUsername());
    }
}
