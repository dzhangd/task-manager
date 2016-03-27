package database;


import connection.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Kwangsoo on 2016-03-19.
 */
public class Project {
    private Connection con;
    public String[] project;

    public Project() {
    }

    public ArrayList getProjects() {
        con = DatabaseConnection.getConnection();
        ArrayList<String[]> projects = new ArrayList<String[]>();
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM Project");
            while(rs.next()) {
                project = new String[3];
                setProject(rs.getString(1), rs.getString(2), rs.getString(3));
                projects.add(project);
            }
            project = null;
            return projects;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setProject(String pid, String name, String description) {
        project[0] = pid;
        project[1] = name;
        project[2] = description;
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
