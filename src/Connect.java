package taskmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Kwangsoo on 2016-03-20.
 */
public class Connect {

    private static Connection connection;

    public Connect() {
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1521:ug", "ora_d8z9a", "a53033149");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            if (!connection.isClosed()) {
                return connection;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return null;
    }
}
