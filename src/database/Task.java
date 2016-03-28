package database;

import connection.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Kwangsoo on 2016-03-27.
 */
public class Task {
    Connection con;
    Object[] task;

    public Task() {
    }

    public ArrayList getTasks() {
        con = DatabaseConnection.getConnection();
        ArrayList<Object[]> tasks = new ArrayList<Object[]>();
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM Task");
            while (rs.next()) {
                task = new Object[10];
                setTask(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getBoolean(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));
                tasks.add(task);
            }
            task = null;
            s.close();
            return tasks;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setTask(int tid, String title, String description, String submittedDate, String estimatedDate,
                        Boolean completed, int priority, int d_id, int m_id, int pid) {
        task[0] = tid;
        task[1] = title;
        task[2] = description;
        task[3] = submittedDate;
        task[4] = estimatedDate;
        task[5] = completed;
        task[6] = priority;
        task[7] = d_id;
        task[8] = m_id;
        task[9] = pid;
    }

    public void addTask(int tid, String title, String description, String submittedDate, String estimatedDate,
                        Boolean completed, int priority, int d_id, int m_id, int pid) {
        con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Task (tid, title, description, submittedDate, " +
                    "estimatedDate, completed, priority, d_id, m_id, pid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, tid);
            ps.setString(2, title);
            ps.setString(3, description);
            ps.setString(4, submittedDate);
            ps.setString(5, estimatedDate);
            ps.setBoolean(6, completed);
            ps.setInt(7, priority);
            ps.setInt(8, d_id);
            ps.setInt(9, m_id);
            ps.setInt(10, pid);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
