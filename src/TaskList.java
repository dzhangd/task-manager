import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class TaskList extends JPanel {

	public TaskList()
	{
		this.setBackground(Color.CYAN);
		
		this.setLayout(new BorderLayout());
		
		DefaultListModel listModel = new DefaultListModel();
        listModel.addElement("Make Database");
        listModel.addElement("Make GUI");
        listModel.addElement("Random Task");
		
		JList taskJList = new JList(listModel);
		taskJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		taskJList.setSelectedIndex(0);
        
		JScrollPane taskScrollPane = new JScrollPane(taskJList);
		this.add(taskScrollPane, BorderLayout.CENTER);
	}
}
