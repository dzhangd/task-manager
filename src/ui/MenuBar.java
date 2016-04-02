package ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {
	
	MenuBar()
	{
		JMenu herp = new JMenu("Herp");
		this.add(herp);
		
		JMenu derp = new JMenu("Derp");
		this.add(derp);
	}
	
}
