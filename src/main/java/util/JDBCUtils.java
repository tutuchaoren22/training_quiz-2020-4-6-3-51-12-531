package util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    public static Connection getConnection() {
        try {
            InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            assert inputStream != null;
            properties.load(inputStream);
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String driverClass = properties.getProperty("driver");
            Class.forName(driverClass);
            return DriverManager.getConnection(url, user, password);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static void closeResource(Connection conn, Statement preparedStatement) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeResource(Connection conn, Statement preparedStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeResource(conn, preparedStatement);
    }
}
