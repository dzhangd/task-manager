package ui;

import connection.DatabaseConnection;
import database.Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.xml.crypto.Data;

public class ProjectList extends JPanel {

    Project project;
    ArrayList<String> projectNames;
    DefaultListModel listModel = new DefaultListModel();

	public ProjectList()
	{
		this.setBackground(Color.GREEN);
		
		this.setLayout(new BorderLayout());

        project = new Project();
        projectNames = new ArrayList<String>();
        setProjectsNames();
        for (int i=0; i<projectNames.size();i++) {
            listModel.addElement(projectNames.get(i));
        }

        
		JList projectJList = new JList(listModel);
		projectJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		projectJList.setSelectedIndex(0);
        
		JScrollPane projectScrollPane = new JScrollPane(projectJList);
		this.add(projectScrollPane, BorderLayout.CENTER);
		
		
		
	}

    public void setProjectsNames() {
        ArrayList<String[]> projects = project.getProjects();
        for (int i=0; i<projects.size(); i++) {
            projectNames.add(projects.get(i)[1]);
        }
    }

}
