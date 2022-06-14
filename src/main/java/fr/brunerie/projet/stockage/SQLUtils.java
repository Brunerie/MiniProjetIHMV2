package fr.brunerie.projet.stockage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLUtils {
    public static Connection connection;

    static {
        String url = "jdbc:mysql://localhost:3306/miniprojetihm";
        String user = "root";
        String password = "";
        String driver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
        }
        catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
