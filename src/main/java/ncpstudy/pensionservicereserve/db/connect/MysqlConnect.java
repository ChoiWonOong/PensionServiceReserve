package ncpstudy.pensionservicereserve.db.connect;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.Map;

public class MysqlConnect {
    static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    File file = new File("src\\main\\resources\\properties.yaml");
    Map<String, Object> properties = new Yaml().load(new FileReader(file.getAbsolutePath()));
    // local DB
    String url = "jdbc:mysql://localhost:3306/study502?serverTimezone=Asia/Seoul";
    String username = properties.get("username").toString();
    String password = properties.get("password").toString();

    public MysqlConnect() throws FileNotFoundException {
        // TODO Auto-generated constructor stub
        try {
            Class.forName(MYSQL_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Mysql Driver 오류:" + e.getMessage());
        }
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("mysql 서버 접속 실패:" + e.getMessage());
        }
        return conn;
    }

    public void dbClose(PreparedStatement pstmt, Connection conn) {
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dbClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}















