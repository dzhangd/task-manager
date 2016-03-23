package database;


import connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static connection.DatabaseConnection.getConnection;

/**
 * Created by Kwangsoo on 2016-03-19.
 */
public class Project {
    Connection con;

    public Project() {
    }



    public void addProject(int pid, String name, String description) {
        con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Project (pid, name, description) VALUES (?, ?, ?)");
            ps.setInt(1,pid);
            ps.setString(2,name);
            ps.setString(3,description);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProject(int pid) {
        con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Project WHERE pid = ?");
            ps.setInt(1, pid);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
