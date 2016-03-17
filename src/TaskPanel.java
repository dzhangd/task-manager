import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TaskPanel extends JPanel{
	
	JLabel title;
	JLabel description;
	
	public TaskPanel()
	{
		GridBagConstraints gbc = new GridBagConstraints();
		
		title = new JLabel("TITLE");
		description = new JLabel("DESCRIPTION");
		
		setLayout(new GridBagLayout());
		
		this.setBackground(Color.GRAY);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		this.add(title, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0.1;
		gbc.weighty = 0.9;
		this.add(description, gbc);
	}
	
}
