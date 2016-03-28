package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import database.Project;
import database.Task;
import ui.ProjectList.ProjectListListener;

public class TaskList extends JPanel {

	static DefaultListModel listModel = new DefaultListModel();

	public TaskList()
	{

		GridBagConstraints gbc = new GridBagConstraints();
		this.setBackground(Color.CYAN);

		this.setLayout(new GridBagLayout());

		JList taskJList = new JList(listModel);
		ListSelectionModel listSelectionModel = taskJList.getSelectionModel();
		listSelectionModel.addListSelectionListener(new TaskListListener());
		taskJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		taskJList.setSelectedIndex(0);

		JScrollPane taskScrollPane = new JScrollPane(taskJList);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 0.85;
		this.add(taskScrollPane, gbc);

		JButton renameTaskButton = new JButton("Rename Task");
		JButton addTaskButton = new JButton("Add Task");
		JButton removeTaskButton = new JButton("Remove Task");

		renameTaskButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Rename Task Button Clicked");
				JTextField newNameField = new JTextField();
				Object[] message = {
						"New name:", newNameField,
				};
				String s = (String)JOptionPane.showInputDialog(null, "Type new name", "Rename", JOptionPane.PLAIN_MESSAGE, null, null, null);
				System.out.println("Rename task to " + s);
			}
		});
		addTaskButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Add Task Button Clicked");
				JTextField taskName = new JTextField();
				JTextField taskDescription = new JTextField();
				Object[] type = {"Bug", "Feature"};
				JComboBox typeCombo = new JComboBox(type);
				Object[] priority = {"1", "2", "3", "4", "5"};
				JComboBox priorityCombo = new JComboBox(priority);
				Object[] message = {
						"Task name:", taskName,
						"Task Description:", taskDescription,
						"Type:", typeCombo,
						"Priority:", priorityCombo
				};
				int addTaskSelection = JOptionPane.showConfirmDialog(null, message, "Add Task", JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION);
				if(addTaskSelection != 0)
				{
					return;
				}
				System.out.println("Task name is " + taskName.getText() + " Task description is " + taskDescription.getText() + " type index is " + typeCombo.getSelectedIndex() + " Priority index is " + priorityCombo.getSelectedIndex());
			}
		});
		removeTaskButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Remove Task Button Clicked");
				Object[] options = {"Yes",
						"No",
				};
				int optionChosen = JOptionPane.showOptionDialog(null,
						"Are you sure you want to remove this task",
						"Remove Task",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						options[1]);
				System.out.println("Remove task option " + optionChosen);
			}
		});


		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 0.05;
		this.add(renameTaskButton, gbc);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 1;
		gbc.weighty = 0.05;
		this.add(addTaskButton, gbc);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 1;
		gbc.weighty = 0.05;
		this.add(removeTaskButton, gbc);
	}

	class TaskListListener implements ListSelectionListener
	{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			ListSelectionModel lsm = (ListSelectionModel)e.getSource();
			int firstIndex = e.getFirstIndex();
			int lastIndex = e.getLastIndex();
			int selectedIndex = lsm.getLeadSelectionIndex();
			System.out.println("Task selected is: " + selectedIndex);
		}

	}

}