package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		
		JMenu derp = new JMenu("Derp");
		this.add(derp);
	}
	
}
