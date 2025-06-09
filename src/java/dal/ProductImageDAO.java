
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ProductImage;


public class ProductImageDAO extends DBContext{
    public List<ProductImage> getAll(){
        List<ProductImage> list = new ArrayList<>();
        String sql=" Select * from ProductImage";
        try {
             PreparedStatement st = connection.prepareStatement(sql);
              ResultSet rs = st.executeQuery();
               while(rs.next()){
                   ProductImage p = new ProductImage(rs.getInt("imageId"), rs.getString("url"), rs.getInt("productId"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    public List<String> getImagesByProductId(int productId){
        List<String> list = new ArrayList<>();
        String sql="Select * from ProductImage where ProductID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productId);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                list.add(rs.getString("url"));
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
}
