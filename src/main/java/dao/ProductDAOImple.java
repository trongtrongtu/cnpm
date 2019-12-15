package dao;

import dao.ProductDAO;
import entity.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Product;
import java.util.Date;

public class ProductDAOImple implements ProductDAO {

    @Override
    public List<Product> getList() {
        Connection con = DBConnect.getConnect();
        String sql = "SELECT * FROM product;";
        List<Product> list = new ArrayList<Product>();
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT * FROM product;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("product_id");
                int categoryID = rs.getInt("Categorycategory_id");
                String name = rs.getString("name");
                float price = rs.getFloat("price");
                String image1 = rs.getString("image1");
                String image2 = rs.getString("image2");
                String image3 = rs.getString("image3");
                String image4 = rs.getString("image4");
                String image5 = rs.getString("image5");
                String description = rs.getString("description");
                String thumbImage = rs.getString("thumb_image");
                Date lastUpdate = rs.getDate("last_update");
                list.add(new Product(productID, name, price, description, image1, image2, image3, image4, image5, thumbImage, lastUpdate, categoryID));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Product getProduct(int id) {
        Connection con = DBConnect.getConnect();
        String sql = "SELECT * FROM PRODUCT WHERE product_id='" + id + "'";
        Product p = new Product();
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("product_id");
                int categoryID = rs.getInt("Categorycategory_id");
                String name = rs.getString("name");
                float price = rs.getFloat("price");
                String image1 = rs.getString("image1");
                String image2 = rs.getString("image2");
                String image3 = rs.getString("image3");
                String image4 = rs.getString("image4");
                String image5 = rs.getString("image5");
                String description = rs.getString("description");
                String thumbImage = rs.getString("thumb_image");
                Date lastUpdate = rs.getDate("last_update");
                p = new Product(productID, name, price, description, image1, image2, image3,
                        image4, image5, thumbImage, lastUpdate, categoryID);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }
       public List<Product> getProductByCategory(int categoryID) {
        Connection con = DBConnect.getConnect();
        String sql = "SELECT * FROM product WHERE ;";
        List<Product> list = new ArrayList<Product>();
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT * FROM product WHERE Categorycategory_id ='"
                    + categoryID + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("product_id");
                String name = rs.getString("name");
                float price = rs.getFloat("price");
                String image1 = rs.getString("image1");
                String image2 = rs.getString("image2");
                String image3 = rs.getString("image3");
                String image4 = rs.getString("image4");
                String image5 = rs.getString("image5");
                String description = rs.getString("description");
                String thumbImage = rs.getString("thumb_image");
                Date lastUpdate = rs.getDate("last_update");
                list.add(new Product(productID, name, price, description, image1, image2, image3, image4, image5, thumbImage, lastUpdate, categoryID));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
       @Override
    public void removeProduct(int product_id) {   
        Connection con = DBConnect.getConnect();
        String sql = "DELETE FROM product WHERE product_id='" + product_id + "';";
        PreparedStatement ps;
        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    @Override
    public void addProduct(Product p) {
        Connection con = DBConnect.getConnect();
        String sql ="INSERT INTO product(name, price, image1, image2, image3, image4, image5, last_update, Categorycategory_id, description) VALUE (?,?,?,?,?,?,?,?,?,?)";
        try {
        PreparedStatement ps  =  (PreparedStatement) con.prepareStatement(sql);
        ps.setString(1,p.getName());
        ps.setFloat(2,p.getPrice());
        ps.setString(3,p.getImage1());
        ps.setString(4,p.getImage2());
        ps.setString(5,p.getImage3());
        ps.setString(6,p.getImage4());
        ps.setString(7,p.getImage5());
        ps.setDate(8, (java.sql.Date) p.getLastUpdate());
        ps.setInt(9,p.getCategoryID());
        ps.setString(10,p.getDescription());
        ps.executeUpdate();
        con.close();
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    @Override
    public void editProduct(Product p) {
        Connection con = DBConnect.getConnect();
        String sql ="UPDATE PRODUCT SET name=?, price=?, image1=?, image2=?, image3=?, image4=?, image5=?, last_update=?, Categorycategory_id=?, description=? WHERE product_id=?";
        try {
        
        PreparedStatement ps  =  (PreparedStatement) con.prepareStatement(sql);
        ps.setString(1,p.getName());
        ps.setFloat(2,p.getPrice());
        ps.setString(3,p.getImage1());
        ps.setString(4,p.getImage2());
        ps.setString(5,p.getImage3());
        ps.setString(6,p.getImage4());
        ps.setString(7,p.getImage5());
        ps.setDate(8, (java.sql.Date) p.getLastUpdate());
        ps.setInt(9,p.getCategoryID());
        ps.setString(10,p.getDescription());
        ps.executeUpdate();
        con.close();
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        //Product p = new Product(0, 1, "S6", "da", 500000.0 ,"","");
//        ProductDAOImple productDAO1 = new ProductDAOImple();
//        productDAO1.getList().forEach((p) -> {
//            System.out.print(p.getImage1());
//        });
//        Product p = productDAO1.getProduct(0);
//                    System.out.print(p.getName());
//
//    }
}
