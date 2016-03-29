package ui;

import database.Project;
import database.Task;

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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import static ui.Startup.taskListPanel;

public class ProjectList extends JPanel {

    JLabel projectDescription = new JLabel("Project Description");
    Project project = new Project();
    ArrayList<Object[]> projects = project.getProjects();
    Task task = new Task();
    ArrayList<Object[]> tasks = task.getTasks();

    public ProjectList()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        this.setBackground(Color.WHITE);

        this.setLayout(new GridBagLayout());

        final DefaultListModel listModel = new DefaultListModel();
        for (int i=0; i<projects.size(); i++) {
            String temp = (String) projects.get(i)[1];
            listModel.addElement(temp.trim());
        }

        JList projectJList = new JList(listModel);
        ListSelectionModel listSelectionModel = projectJList.getSelectionModel();
        listSelectionModel.addListSelectionListener(new ProjectListListener());
        projectJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        projectJList.setSelectedIndex(0);

        JScrollPane projectScrollPane = new JScrollPane(projectJList);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.73;
        this.add(projectScrollPane, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 0.10;
        this.add(projectDescription, gbc);

        JButton renameProjectButton = new JButton("Rename Project");
        JButton addProjectButton = new JButton("Create Project");
        JButton removeProjectButton = new JButton("Delete Project");

        renameProjectButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Rename Project Button Clicked");
                JTextField newNameField = new JTextField();
                Object[] message = {
                        "New name:", newNameField,
                };
                String s = (String)JOptionPane.showInputDialog(null, "Type new name", "Rename", JOptionPane.PLAIN_MESSAGE, null, null, null);
                System.out.println("Rename project to " + s);

                if (s != null) {
                    int pid = (Integer) projects.get(selectedIndex)[0];
                    project.renameProject(pid, s);
                    listModel.set(selectedIndex,s);
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
                String name = (String) projects.get(projects.size() - 1)[1];
                listModel.addElement(name.trim());
            }
        });
        removeProjectButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Remove Project Button Clicked");
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
                int pid = (Integer) projects.get(selectedIndex)[0];
                project.deleteProject(pid);
                listModel.remove(selectedIndex);
            }
        });

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 0.05;
        this.add(renameProjectButton, gbc);
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
    }

    int selectedIndex = 0;

    public class ProjectListListener implements ListSelectionListener
    {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            int firstIndex = e.getFirstIndex();
            int lastIndex = e.getLastIndex();
            selectedIndex = lsm.getLeadSelectionIndex();
            System.out.println("Project selected is: " + selectedIndex);

            String description = (String) projects.get(selectedIndex)[2];
            projectDescription.setText(description.trim());

            taskListPanel.listModel.clear();
            int pid = (Integer) projects.get(selectedIndex)[0];
            for (int i=0; i<tasks.size(); i++) {
                int pid2 = (Integer) tasks.get(i)[9];
                if (pid == pid2) {
                    String title = (String) tasks.get(i)[1];
                    taskListPanel.listModel.addElement(title.trim());
                }
            }
        }

    }

}