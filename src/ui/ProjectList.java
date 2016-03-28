package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ProjectList extends JPanel {

	public ProjectList()
	{
		this.setBackground(Color.GREEN);
		
		this.setLayout(new BorderLayout());
		
		DefaultListModel listModel = new DefaultListModel();
        listModel.addElement("304 project");
        listModel.addElement("Herp project");
        listModel.addElement("Derp Project");
        listModel.addElement("Derpa Project");
        listModel.addElement("Derpb Project");
		
		JList projectJList = new JList(listModel);
	    ListSelectionModel listSelectionModel = projectJList.getSelectionModel();
	    listSelectionModel.addListSelectionListener(new ProjectListListener());
		projectJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		projectJList.setSelectedIndex(0);
        
		JScrollPane projectScrollPane = new JScrollPane(projectJList);
		this.add(projectScrollPane, BorderLayout.CENTER);
	}
	
	class ProjectListListener implements ListSelectionListener
	{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			ListSelectionModel lsm = (ListSelectionModel)e.getSource();
	        int firstIndex = e.getFirstIndex();
	        int lastIndex = e.getLastIndex();
	        int selectedIndex = lsm.getLeadSelectionIndex();
	        System.out.println("Project selected is: " + selectedIndex);
			
		}
		
	}
	
}
