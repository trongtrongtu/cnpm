package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnect {

    public DBConnect() {
    }

    public static Connection getConnect() {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=eMarket;user=letuan;password=letuan123");
            System.out.println("Connect success!");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error when you connect to database!Error is: " + e.getMessage());
        }
        return connection;
    }

    public static void main(String[] args) {
        System.out.println(getConnect());
    }

    public void excuteSql(String sql) throws Exception {
        Connection connect = getConnect();
        PreparedStatement stmt = (PreparedStatement) connect.createStatement();
        stmt.executeUpdate(sql);
    }       

    public ResultSet selectData(String sql) throws Exception {
        Connection connect = getConnect();
        PreparedStatement stmt = (PreparedStatement) connect.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }
}
