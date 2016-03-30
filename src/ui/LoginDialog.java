package ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class LoginDialog extends JDialog {
	private JTextField tfUsername;
	private JPasswordField pfPassword;
	private JLabel lbUsername;
	private JLabel lbPassword;
	private JButton btnLogin;
	private JButton btnExit;
	
	public LoginDialog(Frame parent) {
		super(parent, "Login", true);
		
		JPanel panel = new JPanel(new FlowLayout());
		
		lbUsername = new JLabel("E-mail ");
		panel.add(lbUsername);
		
		tfUsername = new JTextField();
		panel.add(tfUsername);
		
		lbPassword = new JLabel("Password ");
		panel.add(lbPassword);
		
		pfPassword = new JPasswordField();
		panel.add(pfPassword);
		
		btnLogin = new JButton("Login");
		panel.add(btnLogin);
		
		// login listener here...
		
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
		
	}
	
}
