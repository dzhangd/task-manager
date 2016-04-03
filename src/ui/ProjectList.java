package ui;

import database.Project;
import database.Task;
import database.Task.TaskType;
import database.TeamMemberType;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import connection.Session;

public class ProjectList extends JPanel {

	JTextArea projectDescription = new JTextArea("Project Description");
    Project project = new Project();
    ArrayList<Object[]> projects = project.getProjects();
    Task task = new Task();
    ArrayList<Object[]> tasks;
    private TaskList taskListPanel;
    private Session currentSession;
    
    JButton editProjectButton;
    JButton addProjectButton;
    JButton removeProjectButton;
    

    public ProjectList()
    {
    	taskListPanel = Startup.getTaskListPanel();
    	
        GridBagConstraints gbc = new GridBagConstraints();
        this.setPreferredSize(new Dimension(100, 0));
        this.setBackground(Color.WHITE);

        this.setLayout(new GridBagLayout());

        final DefaultListModel listModel = new DefaultListModel();
        for (int i=0; i<projects.size(); i++) {
            String temp = (String) projects.get(i)[1];
            System.out.println(temp);
            if(temp != null)
            {
                listModel.addElement(temp.trim());
            }
            else
            {
                listModel.addElement("ERROR NULL PLZ FIX");	
            }
        }

        JList projectJList = new JList(listModel);
        ListSelectionModel listSelectionModel = projectJList.getSelectionModel();
        listSelectionModel.addListSelectionListener(new ProjectListListener());
        projectJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        projectJList.setSelectedIndex(0);

        JScrollPane projectScrollPane = new JScrollPane(projectJList);
        projectScrollPane.setPreferredSize(new Dimension(100, 200));
        
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.73;
        this.add(projectScrollPane, gbc);

        
        projectDescription.setPreferredSize(new Dimension(100, 50));
        projectDescription.setLineWrap(true);
        projectDescription.setEditable(false);
        
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 0.10;
        this.add(projectDescription, gbc);

        editProjectButton = new JButton("Edit Project");
        addProjectButton = new JButton("Create Project");
        removeProjectButton = new JButton("Delete Project");
        
        editProjectButton.setPreferredSize(new Dimension(100, 22));
        addProjectButton.setPreferredSize(new Dimension(100, 22));
        removeProjectButton.setPreferredSize(new Dimension(100, 22));

        editProjectButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Edit Project Button Clicked");
                JTextField newProjectName = new JTextField();
                JTextField newProjectDescription = new JTextField();
                Object[] message = {
                        "Project name:", newProjectName,
                        "Project Description:", newProjectDescription
                };
                int editProjectSelection = JOptionPane.showConfirmDialog(null, message, "Edit Project", JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION);
                if(editProjectSelection != 0)
                {
                    return;
                }
                System.out.println("New project name is " + newProjectName.getText() + " new project description is " + newProjectDescription.getText());
                System.out.println("Project pid to edit is " + currentPid);
                if (newProjectDescription.getText().isEmpty()) {
                    project.editProject(currentPid, newProjectName.getText(), null);
                } else {
                    project.editProject(currentPid, newProjectName.getText(), newProjectDescription.getText());
                }
                projects = project.getProjects();
                for (int i=0; i< projects.size();i++) {
                    if (currentPid == (Integer) projects.get(i)[0]) {
                        String name = (String) projects.get(i)[1];
                        String description = "";
                        if (projects.get(i)[2] != null) {
                            description = (String) projects.get(i)[2];
                        }
                        listModel.set(i,name.trim());
                        projectDescription.setText(description.trim());
                        return;
                    }
                }
            }
        });
        addProjectButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Add Project Button Clicked");
                JTextField projectName = new JTextField();
                JTextField projectDescription = new JTextField();
                Object[] message = {
                        "Project name:", projectName,
                        "Project Description:", projectDescription
                };
                int addProjectSelection = JOptionPane.showConfirmDialog(null, message, "Add Project", JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION);
                if(addProjectSelection != 0)
                {
                    return;
                }
                System.out.println("Project name is " + projectName.getText() + " Project description is " + projectDescription.getText());

                int maxPid = project.getMaxPid() + 1;
                System.out.println("pid is " + maxPid);
                project.addProject(maxPid, projectName.getText(), projectDescription.getText());
                projects = project.getProjects();
                listModel.addElement("");
                for (int i=0; i<projects.size();i++) {
                    String name = (String) projects.get(i)[1];
                    listModel.set(i,name.trim());
                }
            }
        });
        removeProjectButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Remove Project Button Clicked");
                System.out.println("Pid to remove is " + currentPid);
                Object[] options = {"Yes",
                        "No",
                };
                int optionChosen = JOptionPane.showOptionDialog(null,
                        "Are you sure you want to remove this project",
                        "Remove Project",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[1]);
                System.out.println("Remove project option " + optionChosen);

                if (optionChosen != 0) {
                    return;
                }
                project.deleteProject(currentPid);
                for (int i=0; i<projects.size();i++) {
                    if (currentPid == (Integer) projects.get(i)[0]) {
                        listModel.remove(i);
                        break;
                    }
                }
            }
        });

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 0.05;
        this.add(editProjectButton, gbc);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1;
        gbc.weighty = 0.05;
        this.add(addProjectButton, gbc);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1;
        gbc.weighty = 0.05;
        this.add(removeProjectButton, gbc);
        
        UserChanged();
    }

    int selectedIndex = 0;
    static int currentPid;

    public static ArrayList<Object[]> newTaskList;

    public class ProjectListListener implements ListSelectionListener
    {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting() && taskListPanel.lsm != null) {
                taskListPanel.lsm.clearSelection();
            }
            if (e.getValueIsAdjusting()) {
                return;
            }
            projects = project.getProjects();
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            int firstIndex = e.getFirstIndex();
            int lastIndex = e.getLastIndex();
            selectedIndex = lsm.getLeadSelectionIndex();
            System.out.println("Project selected is: " + selectedIndex);

            currentPid = (Integer) projects.get(selectedIndex)[0];
            System.out.println("Pid selected is " + currentPid);
            String description = "";
            for (int i=0; i<projects.size(); i++) {
                if (currentPid == (Integer) projects.get(i)[0] && projects.get(i)[2] != null) {
                    description = (String) projects.get(i)[2];
                }
            }
            projectDescription.setText(description.trim());

            taskListPanel.listModel.clear();
            tasks = task.getTasks();
            newTaskList = new ArrayList<Object[]>();
            for (int i=0; i<tasks.size(); i++) {
                int pid2 = (Integer) tasks.get(i)[9];
                if (currentPid == pid2) {
                    String title = (String) tasks.get(i)[1];
                    if(title != null)
                    {
                    	if(currentSession != null)
                    	{
                        	if(currentSession.getType() == TeamMemberType.CLIENT && task.getType((int)tasks.get(i)[0]) == TaskType.BUG)
                        	{
                        		continue;
                        	}
                        	else if(currentSession.getType() == TeamMemberType.QUALITY_ASSURANCE && task.getType((int)tasks.get(i)[0]) == TaskType.FEATURE)
                        	{
                        		continue;
                        	}	
                    	}
                    	taskListPanel.listModel.addElement(title.trim());
                    }
                    else
                    {
                    	taskListPanel.listModel.addElement("Should not see this plz fix");
                    }
                    newTaskList.add(tasks.get(i));
                }
            }
        }
    }
    
	public void UserChanged()
	{
		currentSession = Startup.getSession();
        if(currentSession != null) {
            if (currentSession.getType() == TeamMemberType.QUALITY_ASSURANCE) {
                editProjectButton.setEnabled(false);
                addProjectButton.setEnabled(false);
                removeProjectButton.setEnabled(false);
            } else if (currentSession.getType() == TeamMemberType.CLIENT) {
                editProjectButton.setEnabled(false);
                addProjectButton.setEnabled(false);
                removeProjectButton.setEnabled(false);
            } else if (currentSession.getType() == TeamMemberType.MANAGER) {
                editProjectButton.setEnabled(true);
                addProjectButton.setEnabled(true);
                removeProjectButton.setEnabled(true);
            } else if (currentSession.getType() == TeamMemberType.DEVELOPER) {
                editProjectButton.setEnabled(false);
                addProjectButton.setEnabled(false);
                removeProjectButton.setEnabled(false);
            }
        }
	}
    
}