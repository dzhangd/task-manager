package database;


import connection.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Kwangsoo on 2016-03-19.
 */
public class Project {
    private Connection con;
    public Object[] project;
    ArrayList<Object[]> projects;

    public Project() {
    }

    public ArrayList getProjects() {
        con = DatabaseConnection.getConnection();
        projects = new ArrayList<Object[]>();
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM Project");
            while(rs.next()) {
                project = new Object[3];
                setProject(rs.getInt(1), rs.getString(2), rs.getString(3));
                projects.add(project);
            }
            project = null;
            s.close();
            return projects;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setProject(int pid, String name, String description) {
        project[0] = pid;
        project[1] = name;
        project[2] = description;
    }

    public void renameProject(int pid, String name) {
        con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE Project SET name = ? WHERE pid = ?");
            ps.setString(1, name);
            ps.setInt(2,pid);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getMaxPid(){
        con = DatabaseConnection.getConnection();
        int maxPid = 0;
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT MAX(pid) FROM Project");
            if (rs.next()) {
                maxPid = rs.getInt(1);
            }

            s.close();
            return maxPid;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
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
        project = new Object[3];
        project[0] = pid;
        project[1] = name;
        project[2] = description;
        projects.add(project);
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
        for (int i=0; i<projects.size();i++) {
            if ((Integer) projects.get(i)[0] == pid) {
                projects.remove(i);
                return;
            }
        }
    }
}
