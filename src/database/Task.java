package database;

import connection.DatabaseConnection;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.*;
import java.sql.Date;

/**
 * Created by Kwangsoo on 2016-03-27.
 */
public class Task {
    Connection con;
    Object[] task;
    ArrayList<Object[]> tasks;

    public Task() {
    }

    public ArrayList getTasks() {
        con = DatabaseConnection.getConnection();
        tasks = new ArrayList<Object[]>();
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM Task");
            while (rs.next()) {
                task = new Object[10];
                setTask(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5),
                        rs.getDate(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));
                tasks.add(task);
            }
            task = null;
            s.close();
            con.close();
            return tasks;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setTask(int tid, String title, String description, Date submittedDate, String estimatedDate,
                        Date completedDate, int priority, int d_id, int m_id, int pid) {
        task[0] = tid;
        task[1] = title;
        task[2] = description;
        task[3] = submittedDate;
        task[4] = estimatedDate;
        task[5] = completedDate;
        task[6] = priority;
        task[7] = d_id;
        task[8] = m_id;
        task[9] = pid;
    }

    public void editTask(int tid, String title, String description, int priority) {
        con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE Task SET title = ?, description = ?, priority = ? WHERE tid = ?");
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setInt(3, priority);
            ps.setInt(4, tid);

            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getMaxTid() {
        con = DatabaseConnection.getConnection();
        int maxTid = 0;
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT MAX(tid) FROM Task");

            if (rs.next()) {
                maxTid = rs.getInt(1);
            }

            s.close();
            con.close();
            return maxTid;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addTask(int tid, String title, String description, Date submittedDate, String estimatedDate,
                        Date completedDate, int priority, int d_id, int m_id, int pid) {
        con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Task (tid, title, description, submitted_date, estimated_date, completed_date, priority, d_id, m_id, pid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, tid);
            ps.setString(2, title);
            ps.setString(3, description);
            ps.setDate(4, submittedDate);
            ps.setString(5, estimatedDate);
            ps.setDate(6, completedDate);
            ps.setInt(7, priority);
            ps.setInt(8, d_id);
            ps.setInt(9, m_id);
            ps.setInt(10, pid);

            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTask(int tid) {
        con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Task WHERE tid = ?");
            ps.setInt(1, tid);

            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
