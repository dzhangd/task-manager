package ui;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import connection.DatabaseConnection;
import connection.Session;
import database.TeamMember;
import database.TeamMemberType;

public class LoginDialog extends JDialog {
	private JTextField tfEmail;
	private JPasswordField pfPassword;
	private JLabel lbEmail;
	private JLabel lbPassword;
	private JButton btnLogin;
	private JButton btnExit;
	
	public LoginDialog(Startup parent) {
		super(parent, "Login", true);
		

		
		JPanel panel = new JPanel(new FlowLayout());
		
		lbEmail = new JLabel("E-mail ");
		panel.add(lbEmail);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		panel.add(tfEmail);
		
		lbPassword = new JLabel("Password ");
		panel.add(lbPassword);
		
		pfPassword = new JPasswordField();
		pfPassword.setColumns(10);
		panel.add(pfPassword);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String email = tfEmail.getText();
				String password = String.copyValueOf(pfPassword.getPassword());
				if(TeamMember.validate(email, password)) {
					ResultSet rs = TeamMember.findTeamMemberByEmail(email);
			
					try {
						rs.next();
						String name = rs.getString("name");
						name = name.trim();
						int id = rs.getInt("tmid");
						TeamMemberType type = TeamMember.findTypeById(id);
						parent.setSession(new Session(name, id, type));
						JOptionPane.showMessageDialog(LoginDialog.this,
								"Welcome " + name + ".",
								"Login",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(LoginDialog.this,
                            "Invalid email or password",
                            "Login",
                            JOptionPane.ERROR_MESSAGE);
                    // reset email and password
                    tfEmail.setText("");
                    pfPassword.setText("");
				}
			}
			
		});
		panel.add(btnLogin);
		
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
			@Override
			public void windowOpened(WindowEvent e) {}

			@Override
			public void windowActivated(WindowEvent arg0) {}

			@Override
			public void windowClosed(WindowEvent arg0) {}

			@Override
			public void windowDeactivated(WindowEvent arg0) {}

			@Override
			public void windowDeiconified(WindowEvent arg0) {}

			@Override
			public void windowIconified(WindowEvent arg0) {	}
			
		});
		
		panel.add(btnExit);
		
		setContentPane(panel);
		setLocationRelativeTo(parent);
		setSize(new Dimension(400, 100));
		setResizable(false);
		
		
	}

	
}
