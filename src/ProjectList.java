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

public class ProjectList extends JPanel {

	public ProjectList()
	{
		this.setBackground(Color.GREEN);
		
		this.setLayout(new BorderLayout());
		
		DefaultListModel listModel = new DefaultListModel();
        listModel.addElement("304 project");
        listModel.addElement("Herp project");
        listModel.addElement("Derp Project");
		
		JList projectJList = new JList(listModel);
		projectJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		projectJList.setSelectedIndex(0);
        
		JScrollPane projectScrollPane = new JScrollPane(projectJList);
		this.add(projectScrollPane, BorderLayout.CENTER);
		
		
		
	}
	
}
