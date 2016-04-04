package ui;

import connection.DatabaseConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MenuBar extends JMenuBar {
	
	MenuBar()
	{
		JMenu file = new JMenu("File");
		JMenuItem logOut = new JMenuItem("Log out");
		logOut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Startup.setSession(null);
				Startup.showLoginDialog();
			}
		});
		file.add(logOut);
		this.add(file);
		
		JMenu find = new JMenu("Find");
		JMenuItem lowest = new JMenuItem("Find Developer");
		lowest.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] options = {"SLOWEST COMPLETION",
									"FASTEST COMPLETION",
									"CANCEL"};
				int selection = JOptionPane.showOptionDialog(null, "WHICH DEVELOPER WOULD YOU LIKE TO FIND: ", "FIND DEVELOPER", JOptionPane.OK_OPTION, JOptionPane.DEFAULT_OPTION, null, options , null);
				System.out.println("SELECTED IS: " + selection);
				String temp = "";
				String temp2 = "";
				if (selection == 0) {
					temp = "MAX";
					temp2 = "SLOWEST DEVELOPER";
				}
				if (selection == 1) {
					temp = "MIN";
					temp2 = "FASTEST DEVELOPER";
				}
				if (selection == 2) {
					return;
				}
				String developer = "";
				Connection con = DatabaseConnection.getConnection();
				try {
					Statement s = con.createStatement();
					ResultSet rs = s.executeQuery("SELECT TM.name FROM TeamMember TM, Developer D, Task T WHERE TM.tmid = D.tmid and D.tmid = T.d_id and T.d_id = " +
					"(SELECT e.d_id " +
							"FROM (SELECT d_id, AVG(EXTRACT (MINUTE FROM (completed_date - submitted_date)) + (EXTRACT (HOUR FROM (completed_date - submitted_date)))*60 + (EXTRACT (DAY FROM (completed_date - submitted_date)))*24*60) as AvgTime " +
							"FROM Task " +
							"GROUP BY d_id) e " +
							"WHERE e.AvgTime = (SELECT " + temp + "(f.AvgTime) " +
											   "FROM (SELECT d_id, AVG(EXTRACT (MINUTE FROM (completed_date - submitted_date)) + (EXTRACT (HOUR FROM (completed_date - submitted_date)))*60 + (EXTRACT (DAY FROM (completed_date - submitted_date)))*24*60) as AvgTime " +
									           "FROM Task " +
											   "GROUP BY d_id) f))");
					if (rs.next()) {
						developer = rs.getString(1);
					}
					s.close();
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null,"NAME: "+developer, temp2,JOptionPane.PLAIN_MESSAGE);
			}
		});

		JMenuItem contains = new JMenuItem("Contains all team members");
		contains.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> members = new ArrayList<String>();
				Connection con = DatabaseConnection.getConnection();
				try {
					Statement s = con.createStatement();

					ResultSet rs = s.executeQuery("SELECT name " +
						"FROM Project " +
						"WHERE pid IN ( " +
							"SELECT pid " +
							"FROM PTMContains " +
							"WHERE tmid IN ( "+
							             "SELECT tmid " +
							             "FROM TeamMember " +
							           ") " +
							"GROUP BY pid " +
							"HAVING COUNT(*) = ( " + 
							                    "SELECT COUNT (*) " +
							                    "FROM TeamMember " +
							                  "))");
					while (rs.next()) {
						members.add(rs.getString(1));
					}
					s.close();
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if (members.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No project is found", "Project", JOptionPane.PLAIN_MESSAGE);
				} else {
					for (int i = 0; i < members.size(); i++) {
						JOptionPane.showMessageDialog(null, members.get(i), "Project", JOptionPane.PLAIN_MESSAGE);
					}
				}
			}
		});

		find.add(contains);
		find.add(lowest);
		this.add(find);
	}
	
}
