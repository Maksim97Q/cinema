package by.academy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDAO {
    private static final String url = "jdbc:mysql://127.0.0.1:3307/cinema";
    private static final String username = "root";
    private static final String password = "";
    private static final String driver = ("com.mysql.jdbc.Driver");
    private static Connection conn = null;

    public static Connection connection() throws SQLException {
        try {
            Class.forName(driver);
            if (conn == null) {
                conn = DriverManager.getConnection(url, username, password);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("НЕ подключилось", e);
        }
        return conn;
    }

    public static void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                try {
                    throw new SQLException("не закрылся " + e);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
