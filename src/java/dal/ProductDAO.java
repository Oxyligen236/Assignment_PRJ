package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product;

public class ProductDAO extends DBContext {

//      private int productId;
//    private String productName;
//    private int productQuantity;
//    private String description;
//    private double money;
//    private int typeId;
//    private int brandId;
//    private String createdAt;
//    private String updatedAt;       
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        String sql = "select * from Product";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt("productId"), rs.getString("productName"),
                        rs.getInt("productQuantity"), rs.getString("description"), rs.getDouble("price"), rs.getInt("typeId"), rs.getInt("brandId"), rs.getString("createdAt"), rs.getString("updatedAt"));
                productList.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return productList;
    }

    //delete
    public void remove(int productId) {
        String sql = "delete from Product where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Product getProductById(int id) {
        String sql = "Select * from Product where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Product(id, rs.getString("productName"),
                        rs.getInt("productQuantity"), rs.getString("description"), rs.getDouble("price"), rs.getInt("typeId"), rs.getInt("brandId"), rs.getString("createdAt"), rs.getString("updatedAt"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //update
    public void update(Product p) {
        String sql = "update Product set productName=?, productQuantity=?, description=?, price=?, typeId=?, brandId=?, createdAt=? updatedAt=? where productId=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, p.getProductName());
            st.setInt(2, p.getProductQuantity());
            st.setString(3, p.getDescription());
            st.setDouble(4, p.getPrice());
            st.setInt(5, p.getTypeId());
            st.setInt(6, p.getBrandId());
            st.setString(7, p.getCreatedAt());
            st.setString(8, p.getUpdatedAt());
            st.setInt(9, p.getProductId());

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //insert
    public void insert(Product p) {
        String sql = "INSERT INTO [dbo].[Product]\n"
                + "           ([ProductName]\n"
                + "           ,[ProductQuantity]\n"
                + "           ,[Description]\n"
                + "           ,[Price]\n"
                + "           ,[TypeID]\n"
                + "           ,[BrandID]\n"
                + "           ,[CreatedAt]\n"
                + "           ,[UpdatedAt])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, p.getProductName());
            st.setInt(2, p.getProductQuantity());
            st.setString(3,p.getDescription() );
            st.setDouble(3, p.getPrice());
            st.setInt(3,p.getTypeId() );
            st.setInt(3, p.getBrandId());
            st.setString(3,p.getCreatedAt() );
            st.setString(3,p.getUpdatedAt());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        ProductDAO c = new ProductDAO();
        List<Product> list = c.getAll();
        System.out.println(list.get(0).getProductName());
    }
}
