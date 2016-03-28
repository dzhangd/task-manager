package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ui.ProjectList.ProjectListListener;

public class TaskList extends JPanel {

	public TaskList()
	{
		GridBagConstraints gbc = new GridBagConstraints();
		this.setBackground(Color.CYAN);
		
		this.setLayout(new GridBagLayout());
		
		DefaultListModel listModel = new DefaultListModel();
        listModel.addElement("Make Database");
        listModel.addElement("Make GUI");
        listModel.addElement("Random Task");
        listModel.addElement("Random Task2");
        listModel.addElement("Random Task3");
		
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
		JButton addTaskButton = new JButton("Create Task");
		JButton removeTaskButton = new JButton("Delete Task");
		
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
