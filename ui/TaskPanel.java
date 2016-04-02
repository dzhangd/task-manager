package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TaskPanel extends JPanel{

	JLabel title;
	JTextArea descriptionArea;
	JScrollPane descriptionScrollPane;
	JLabel createdByLabel;
	JLabel assignedToLabel;
	JLabel mangedByLabel;
	JLabel priorityLabel;
	JLabel createdDate;
	JLabel estimatedDate;
	JLabel completedDate;

	public TaskPanel()
	{
		GridBagConstraints gbc = new GridBagConstraints();
		this.setPreferredSize(new Dimension(500, 500));
		title = new JLabel("TITLE OF TASK");
		descriptionArea = new JTextArea("TASK DESCRIPTION");
		descriptionArea.setEditable(false);
		descriptionScrollPane = new JScrollPane(descriptionArea);

		createdByLabel = new JLabel("CREATED BY: ");
		createdByLabel.setOpaque(true);
		assignedToLabel = new JLabel("ASSIGNED TO: ");
		assignedToLabel.setOpaque(true);
		mangedByLabel = new JLabel("MANAGED BY: ");
		mangedByLabel.setOpaque(true);
		priorityLabel = new JLabel("PRIORITY: ");
		priorityLabel.setOpaque(true);
		createdDate = new JLabel("CREATED: ");
		createdDate.setOpaque(true);
		estimatedDate = new JLabel("ESTIMATED: ");
		estimatedDate.setOpaque(true);
		completedDate = new JLabel("COMPLETED: ");
		completedDate.setOpaque(true);

		setLayout(new GridBagLayout());

		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.8;
		gbc.weighty = 0.05;
		this.add(this.title, gbc);
		gbc.fill = GridBagConstraints.BOTH;;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0.8;
		gbc.weighty = 0.95;
		gbc.gridheight = 8;
		this.add(descriptionScrollPane, gbc);



		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 0.2;
		gbc.weighty = 0;
		gbc.gridheight = 1;
		this.add(createdByLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 0.2;
		this.add(assignedToLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.weightx = 0.2;
		this.add(mangedByLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.weightx = 0.2;
		this.add(priorityLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.weightx = 0.2;
		this.add(createdDate, gbc);
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.weightx = 0.2;
		this.add(estimatedDate, gbc);
		gbc.gridx = 1;
		gbc.gridy = 7;
		gbc.weightx = 0.2;
		this.add(completedDate, gbc);
	}

}