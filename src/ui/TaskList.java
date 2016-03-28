package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
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
		this.setBackground(Color.CYAN);
		
		this.setLayout(new BorderLayout());
		
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
		this.add(taskScrollPane, BorderLayout.CENTER);
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
