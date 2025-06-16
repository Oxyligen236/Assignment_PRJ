package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Brand;
import model.Product;
import model.Type;

public class ProductDAO extends DBContext {

//   private int productId;
//    private String productName;
//    private int productQuantity;
//    private String description;
//    private double price;
//    private Type type;
//    private Brand brand;
//    private String createdAt;
//    private String updatedAt;      
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        String sql = "select * from Product";
        TypeDAO td = new TypeDAO();
        BrandDAO bd = new BrandDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("productId"));
                p.setProductName(rs.getString("productName"));
                p.setProductQuantity(rs.getInt("productQuantity"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                Type e = td.getTypeById(rs.getInt("TypeID"));
                p.setType(e);
                Brand b = bd.getBrandById(rs.getInt("BrandID"));
                p.setBrand(b);
                p.setCreatedAt(rs.getString("createdAt"));
                p.setUpdatedAt(rs.getString("updatedAt"));
                productList.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return productList;
    }

    //delete
    public void remove(int productId) {
        String sql = "delete from Product where id= ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Product getProductById(int id) {
        String sql = "Select * from Product where ProductID=?";
        TypeDAO td = new TypeDAO();
        BrandDAO bd = new BrandDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("productId"));
                p.setProductName(rs.getString("productName"));
                p.setProductQuantity(rs.getInt("productQuantity"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                Type e = td.getTypeById(rs.getInt("TypeID"));
                p.setType(e);
                Brand b = bd.getBrandById(rs.getInt("BrandID"));
                p.setBrand(b);
                p.setCreatedAt(rs.getString("createdAt"));
                p.setUpdatedAt(rs.getString("updatedAt"));
                return p;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //update
    public void update(Product p) {
        String sql = "update Product set productName=?, productQuantity=?, description=?, price=?, typeId=?, brandId=?, createdAt=? updatedAt=? where productId=?";
        TypeDAO td = new TypeDAO();
        BrandDAO bd = new BrandDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, p.getProductName());
            st.setInt(2, p.getProductQuantity());
            st.setString(3, p.getDescription());
            st.setDouble(4, p.getPrice());
            st.setInt(5, p.getType().getTypeID());
            st.setInt(6, p.getBrand().getBrandId());
            st.setString(7, p.getCreatedAt());
            st.setString(8, p.getUpdatedAt());
            st.setInt(9, p.getProductId());

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Product> getProductbyBrandId(int brandId) {
        String sql = "SELECT [ProductID]\n"
                + "      ,[ProductName]\n"
                + "      ,[ProductQuantity]\n"
                + "      ,[Description]\n"
                + "      ,[Price]\n"
                + "      ,[TypeID]\n"
                + "      ,[BrandID]\n"
                + "      ,[CreatedAt]\n"
                + "      ,[UpdatedAt]\n"
                + "  FROM [dbo].[Product]\n"
                + "  where 1=1";
        
        TypeDAO td = new TypeDAO();
        BrandDAO bd = new BrandDAO();
        if (brandId != 0) {
            sql += " and BrandID=?";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            if (brandId != 0) {
                st.setInt(1, brandId);
            }

            ResultSet rs = st.executeQuery();
            List<Product> list = new ArrayList<>();
            while (rs.next()) {
                 Product p = new Product();
                p.setProductId(rs.getInt("productId"));
                p.setProductName(rs.getString("productName"));
                p.setProductQuantity(rs.getInt("productQuantity"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                Type e = td.getTypeById(rs.getInt("TypeID"));
                p.setType(e);
                Brand b = bd.getBrandById(rs.getInt("BrandID"));
                p.setBrand(b);
                p.setCreatedAt(rs.getString("createdAt"));
                p.setUpdatedAt(rs.getString("updatedAt"));
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public List<Product> getProductbyTypeId(int typeId) {
        String sql = "SELECT [ProductID]\n"
                + "      ,[ProductName]\n"
                + "      ,[ProductQuantity]\n"
                + "      ,[Description]\n"
                + "      ,[Price]\n"
                + "      ,[TypeID]\n"
                + "      ,[BrandID]\n"
                + "      ,[CreatedAt]\n"
                + "      ,[UpdatedAt]\n"
                + "  FROM [dbo].[Product]\n"
                + "  where 1=1";
        
        TypeDAO td = new TypeDAO();
        BrandDAO bd = new BrandDAO();
        if (typeId != 0) {
            sql += " and TypeID=?";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            if (typeId != 0) {
                st.setInt(1, typeId);
            }

            ResultSet rs = st.executeQuery();
            List<Product> list = new ArrayList<>();
            while (rs.next()) {
                 Product p = new Product();
                p.setProductId(rs.getInt("productId"));
                p.setProductName(rs.getString("productName"));
                p.setProductQuantity(rs.getInt("productQuantity"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                Type e = td.getTypeById(rs.getInt("TypeID"));
                p.setType(e);
                Brand b = bd.getBrandById(rs.getInt("BrandID"));
                p.setBrand(b);
                p.setCreatedAt(rs.getString("createdAt"));
                p.setUpdatedAt(rs.getString("updatedAt"));
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
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
            st.setString(3, p.getDescription());
            st.setDouble(4, p.getPrice());
            st.setInt(5, p.getType().getTypeID());
            st.setInt(6, p.getBrand().getBrandId());
            st.setString(7, p.getCreatedAt());
            st.setString(8, p.getUpdatedAt());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        ProductDAO c = new ProductDAO();
        Product p=c.getProductById(1);
        System.out.println(p.getPrice());
        //List<Product> list = c.getAll();
        //System.out.println(list.get(0).getProductName());
    }
}
