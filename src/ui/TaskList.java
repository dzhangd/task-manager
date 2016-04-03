package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import connection.DatabaseConnection;
import connection.Session;
import database.Project;
import database.Task;
import database.TeamMemberType;
import ui.ProjectList.ProjectListListener;
import static ui.ProjectList.currentPid;
import static ui.ProjectList.newTaskList;


public class TaskList extends JPanel {

	static DefaultListModel listModel = new DefaultListModel();
	Task task = new Task();
	ArrayList<Object[]> tasks = task.getTasks();
	JScrollPane taskScrollPane;
	JPanel taskAttributeButtonPanel;
	JScrollPane taskAttributeScrollPane;
	
	JButton filterButton;
	
	JButton editTaskButton;
	JButton addTaskButton;
	JButton removeTaskButton;
	
	JCheckBox nameAttributeBox;
	JCheckBox descriptionAttributeBox;
	JCheckBox typeAttributeBox;
	JCheckBox priorityAttributeBox;
	JCheckBox createdByAttributeBox;
	JCheckBox assignedToByAttributeBox;
	JCheckBox managedByAttributeBox;
	JCheckBox createdOntributeBox;
	JCheckBox estimatedAttributeBox;
	JCheckBox completedAttributeBox;
	
	private TaskPanel taskPanel;
	private Session currentSession;
	
	public TaskList()
	{
		taskPanel = Startup.getTaskPanel();
		currentSession = Startup.getSession();
		
		GridBagConstraints gbc = new GridBagConstraints();
		this.setBackground(Color.CYAN);

		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(100, 0));
		
		final JList taskJList = new JList(listModel);
		final ListSelectionModel listSelectionModel = taskJList.getSelectionModel();
		listSelectionModel.addListSelectionListener(new TaskListListener());
		taskJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		taskScrollPane = new JScrollPane(taskJList);
		taskScrollPane.setPreferredSize(new Dimension(100, 200));
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 0.75;
		this.add(taskScrollPane, gbc);
		
		taskAttributeButtonPanel = new JPanel();
		taskAttributeButtonPanel.setPreferredSize(new Dimension(100, 50));
		taskAttributeButtonPanel.setLayout(new GridLayout(5, 2));
		taskAttributeScrollPane = new JScrollPane(taskAttributeButtonPanel);
		
		
		
		nameAttributeBox = new JCheckBox("name");
		descriptionAttributeBox = new JCheckBox("description");
		typeAttributeBox = new JCheckBox("type");
		priorityAttributeBox = new JCheckBox("priority");
		createdByAttributeBox = new JCheckBox("created by");
		assignedToByAttributeBox = new JCheckBox("assigned");
		managedByAttributeBox = new JCheckBox("managed");
		createdOntributeBox = new JCheckBox("created");
		estimatedAttributeBox = new JCheckBox("estimated");
		completedAttributeBox = new JCheckBox("completed");
		
		nameAttributeBox.setSelected(true);
		descriptionAttributeBox.setSelected(true);
		typeAttributeBox.setSelected(true);
		priorityAttributeBox.setSelected(true);
		createdByAttributeBox.setSelected(true);
		assignedToByAttributeBox.setSelected(true);
		managedByAttributeBox.setSelected(true);
		createdOntributeBox.setSelected(true);
		estimatedAttributeBox.setSelected(true);
		completedAttributeBox.setSelected(true);
		
		
		taskAttributeButtonPanel.add(nameAttributeBox);
		taskAttributeButtonPanel.add(descriptionAttributeBox);
		taskAttributeButtonPanel.add(typeAttributeBox);
		taskAttributeButtonPanel.add(priorityAttributeBox);
		taskAttributeButtonPanel.add(createdByAttributeBox);
		taskAttributeButtonPanel.add(assignedToByAttributeBox);
		taskAttributeButtonPanel.add(managedByAttributeBox);
		taskAttributeButtonPanel.add(createdOntributeBox);
		taskAttributeButtonPanel.add(estimatedAttributeBox);
		taskAttributeButtonPanel.add(completedAttributeBox);
		
		
		

		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		this.add(taskAttributeScrollPane, gbc);
		
		filterButton = new JButton("Set Filter");
		filterButton.setPreferredSize(new Dimension(100, 20));
		filterButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Filter Button Clicked");
				currentSession = Startup.getSession();
				if(currentSession != null)
				{
					JTextField filterTaskName = new JTextField();
					JTextField filterTaskDescription = new JTextField();
					JTextField filterCreatedBy = new JTextField();
					JTextField filterManagedBy = new JTextField();
					JTextField filterAssignedTo = new JTextField();
					Object[] type = {"All", "Bug", "Feature"};
					JComboBox newTypeCombo = new JComboBox(type);
					Object[] priority = {"1", "2", "3", "4", "5"};
					JComboBox minPriorityCombo = new JComboBox(priority);
					JComboBox maxPriorityCombo = new JComboBox(priority);
					Object[] message = {
							"Task name:", filterTaskName,
							"Task Description:", filterTaskDescription,
							"Created by:", filterCreatedBy,
							"Managed by:", filterManagedBy,
							"Assigned to:", filterAssignedTo,
							"Type:", newTypeCombo,
							"Min priority:", minPriorityCombo,
							"Max priority:", maxPriorityCombo
					};
					int filterTaskSelection = JOptionPane.showConfirmDialog(null, message, "Filter Task", JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION);
					if(filterTaskSelection != 0)
					{
						return;
					}
					System.out.println("Filter name is " + filterTaskName.getText() + " Filter description is " +
					filterTaskDescription.getText() + " type index is " + newTypeCombo.getSelectedIndex() +
					" Min priority index is " + minPriorityCombo.getSelectedIndex() + " Max priority index is " +
					maxPriorityCombo.getSelectedIndex() + " Created by is: " + filterCreatedBy.getText() + " Managed by is: " + filterManagedBy.getText() + " Assigned to is: " + filterAssignedTo.getText());
	
					// TODO: change stuff when users are available, but for now:
				}
			}
		});
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 1;
		gbc.weighty = 0.05;
		this.add(filterButton, gbc);
		
		
		
		editTaskButton = new JButton("Edit Task");
		addTaskButton = new JButton("Add Task");
		removeTaskButton = new JButton("Remove Task");
		editTaskButton.setPreferredSize(new Dimension(100, 20));
		addTaskButton.setPreferredSize(new Dimension(100, 20));
		removeTaskButton.setPreferredSize(new Dimension(100, 20));

		editTaskButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Edit Task Button Clicked");
				currentSession = Startup.getSession();
				if(currentSession != null)
				{
					JTextField newTaskName = new JTextField();
					JTextField newTaskDescription = new JTextField();
					Object[] type = {"should not see this"};
					if (currentSession.getType() == TeamMemberType.QUALITY_ASSURANCE)
					{
						type = new Object[]{"Bug"};
					}
					else if (currentSession.getType() == TeamMemberType.CLIENT)
					{
						type = new Object[]{"Feature"};
					}
					Object[] priority = {"1", "2", "3", "4", "5"};
					JComboBox newPriorityCombo = new JComboBox(priority);
					JTextField setEstimatedDate = new JTextField();
					JTextField setCompletedDate = new JTextField();
					JButton setCompleted = new JButton();
					setCompleted.setPreferredSize(new Dimension(50, 50));
					setCompleted.setText("Set completed to now");
					SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm");;
					setCompleted.addActionListener(new ActionListener()
							{

								@Override
								public void actionPerformed(ActionEvent arg0) {
									
								    Date now = new Date();
								    String strDate = sdfDate.format(now);
									
									setCompletedDate.setText(strDate);
								}
						
							});
                    if(currentSession.getType()!= TeamMemberType.DEVELOPER)
                    {
                    	setCompletedDate.setEnabled(false);
                    	setEstimatedDate.setEnabled(false);
                    	setCompleted.setEnabled(false);
					}
					
                    newTaskName.setText(taskPanel.title.getText());
                    newTaskDescription.setText(taskPanel.descriptionArea.getText());
                    newPriorityCombo.setSelectedIndex(Integer.parseInt(taskPanel.priorityLabel.getText().substring(taskPanel.priorityLabel.getText().length()-1, taskPanel.priorityLabel.getText().length())) - 1);
                    setEstimatedDate.setText(taskPanel.estimatedDate.getText().replace("ESTIMATED: ", ""));
                    setCompletedDate.setText(taskPanel.completedDate.getText().replace("COMPLETED: ", ""));
                    
					Object[] message = {
							"Task name:", newTaskName,
							"Task Description:", newTaskDescription,
							"Priority:", newPriorityCombo,
							"Estimated Date (yyyy-MM-dd HH:mm):", setEstimatedDate,
							"Completed (yyyy-MM-dd HH:mm):", setCompletedDate,
							"Set completed to now", setCompleted
					};
					int editTaskSelection = JOptionPane.showConfirmDialog(null, message, "Edit Task", JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION);
					if(editTaskSelection != 0)
					{
						return;
					}
					java.sql.Timestamp estimatedTimestamp = null;
					java.sql.Timestamp completedTimestamp = null;
					
					if(setEstimatedDate.getText() != null && !setEstimatedDate.getText().isEmpty())
					{
						try {
							Date estimatedDate = sdfDate.parse(setEstimatedDate.getText());
							estimatedTimestamp = new java.sql.Timestamp(estimatedDate.getTime());
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Estimated date format is incorrect, please use yyyy-MM-dd HH:mm", "Date format error", JOptionPane.ERROR_MESSAGE);
							e.printStackTrace();
						}
					}
					
					if(setCompletedDate.getText() != null && !setCompletedDate.getText().isEmpty())
					{
						try {
							Date completedDate = sdfDate.parse(setCompletedDate.getText());
							completedTimestamp = new java.sql.Timestamp(completedDate.getTime());
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Completed date format is incorrect, please use yyyy-MM-dd HH:mm", "Date format error", JOptionPane.ERROR_MESSAGE);
							e.printStackTrace();
						}
					}
					
					
					System.out.println("Task name is " + newTaskName.getText() + " Task description is " + newTaskDescription.getText() + " Priority index is " + newPriorityCombo.getSelectedIndex());
	
					// TODO: change stuff when users are available, but for now:
					System.out.println("Task tid to edit is " + currentTid);
					int pri = Integer.parseInt((String) newPriorityCombo.getSelectedItem());
					
					if (newTaskDescription.getText().isEmpty()) {
						task.editTask(currentTid, newTaskName.getText(), null, pri, completedTimestamp, estimatedTimestamp);
					} else {
						task.editTask(currentTid, newTaskName.getText(), newTaskDescription.getText(), pri, completedTimestamp, estimatedTimestamp);
					}
					listModel.set(selectedIndex,newTaskName.getText());
					taskPanel.title.setText(newTaskName.getText());
					taskPanel.priorityLabel.setText("PRIORITY: " + String.valueOf(newPriorityCombo.getSelectedItem()));
					if (newTaskDescription.getText().isEmpty()) {
						taskPanel.descriptionArea.setText("");
					} else {
						taskPanel.descriptionArea.setText(newTaskDescription.getText());
					}
					tasks = task.getTasks();
				}
			}
		});
	addTaskButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Add Task Button Clicked");
				currentSession = Startup.getSession();
				if(currentSession != null)
				{
					JTextField taskName = new JTextField();
					JTextField taskDescription = new JTextField();
					Object[] type = {"should not see this"};
					if (currentSession.getType() == TeamMemberType.QUALITY_ASSURANCE)
					{
						type = new Object[]{"Bug"};
					}
					else if (currentSession.getType() == TeamMemberType.CLIENT)
					{
						type = new Object[]{"Feature"};
					}
					
					JComboBox typeCombo = new JComboBox(type);
					Object[] priority = {"1", "2", "3", "4", "5"};
					JComboBox priorityCombo = new JComboBox(priority);
					Object[] message = {
							"Task name:", taskName,
							"Task Description:", taskDescription,
							"Type:", typeCombo,

							"Priority:", priorityCombo,
							

					};
					int addTaskSelection = JOptionPane.showConfirmDialog(null, message, "Add Task", JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION);
					if(addTaskSelection != 0)
					{
						return;
					}
	
					// TODO: change later when available!
					System.out.println("Task name is " + taskName.getText() + " Task description is " + taskDescription.getText() + " type index is " + typeCombo.getSelectedIndex() + " Priority index is " + priorityCombo.getSelectedIndex());
	
					int tid = task.getMaxTid() + 1;
					System.out.println("tid is " + tid);

					//java.sql.Date comDate = new java.sql.Date(new java.util.Date().getTime());
					//task.addTask(tid, taskName.getText(), taskDescription.getText(), subDate, estimatedDate.getText(), subDate, priorityCombo.getSelectedIndex() + 1, 1031, 1033, currentPid);
					//String p = (String) priorityCombo.getSelectedItem();
					 //int pri = Integer.parseInt(p);
					 				//task.addTask(tid, taskName.getText(), taskDescription.getText(), subTime, estimatedDate.getText(), subDate, pri, 1031, 1033, currentPid);

					java.sql.Timestamp subTime = new java.sql.Timestamp(new java.util.Date().getTime());
					String p = (String) priorityCombo.getSelectedItem();
					int pri = Integer.parseInt(p);
					task.addTask(tid, taskName.getText(), taskDescription.getText(), subTime, null, null, pri, 1031, 1033, currentPid);	

					tasks = task.getTasks();
					ArrayList<Object[]> temp = new ArrayList<Object[]>();
					for (int i=0; i<tasks.size();i++) {
						if (currentPid==(Integer) tasks.get(i)[9]) {
							temp.add(tasks.get(i));
						}
					}
					newTaskList = temp;
					listModel.addElement("");
					for (int i=0; i<newTaskList.size(); i++) {
						String title = (String) newTaskList.get(i)[1];
						listModel.set(i,title.trim());
					}
				}
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

				if (optionChosen != 0) {
					return;
				}
				task.deleteTask(currentTid);
				for (int i=0; i<newTaskList.size();i++) {
					if (currentTid == (Integer) newTaskList.get(i)[0]) {
						listModel.remove(i);
						break;
					}
				}
			}
		});


		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 1;
		gbc.weighty = 0.05;
		this.add(editTaskButton, gbc);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.weightx = 1;
		gbc.weighty = 0.05;
		this.add(addTaskButton, gbc);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.weightx = 1;
		gbc.weighty = 0.05;
		this.add(removeTaskButton, gbc);
		
		UserChanged();
	}

	private int selectedIndex;
	ListSelectionModel lsm;
	static int currentTid = 0;

	class TaskListListener implements ListSelectionListener
	{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				return;
			}
			clearLabels();
			lsm = (ListSelectionModel)e.getSource();
			int firstIndex = e.getFirstIndex();
			int lastIndex = e.getLastIndex();
			selectedIndex = lsm.getLeadSelectionIndex();
			System.out.println("Task selected is: " + selectedIndex + " Attribute boxes are : " + nameAttributeBox.isSelected() + " " + descriptionAttributeBox.isSelected()
			+ " " + typeAttributeBox.isSelected() + " " + priorityAttributeBox.isSelected() + " " + createdByAttributeBox.isSelected()
			+ " " + assignedToByAttributeBox.isSelected() + " " + managedByAttributeBox.isSelected() + " " + createdOntributeBox.isSelected()
			+ " " + estimatedAttributeBox.isSelected() + " " + completedAttributeBox.isSelected());

			currentTid = (Integer) newTaskList.get(selectedIndex)[0];
			System.out.println("Current tid selected is " + currentTid);

			int count = 1;
			String title,description,subDate,estDate,completed,priority,d_id,m_id;
			title = description = subDate = estDate = completed = priority = d_id = m_id = "";
			if (nameAttributeBox.isSelected()) {
				title = "title, ";
				count++;
			}
			if (descriptionAttributeBox.isSelected()) {
				description = "description, ";
				count ++;
			}
			if (createdOntributeBox.isSelected()) {
				subDate = "submitted_date, ";
				count++;
			}
			if (estimatedAttributeBox.isSelected()) {
				estDate = "estimated_date, ";
				count++;
			}
			if (completedAttributeBox.isSelected()) {
				completed = "completed_date, ";
				count ++;
			}
			if (priorityAttributeBox.isSelected()) {
				priority = "priority, ";
				count++;
			}
			if (assignedToByAttributeBox.isSelected()) {
				d_id = "d_id, ";
				count++;
			}
			if (managedByAttributeBox.isSelected()) {
				m_id = "m_id, ";
				count++;
			}
			String sqlQuery = "SELECT tid, " + title + description +
					subDate + estDate + completed + priority + d_id + m_id + "pid " +
					"FROM Task WHERE tid = " + String.valueOf(currentTid);




			Connection con = DatabaseConnection.getConnection();
			try {
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sqlQuery);
				if (rs.next()) {
					
					String tempString;
					
					if (managedByAttributeBox.isSelected())
					{
						tempString = rs.getString(count);
						if(tempString != null)
						{
							taskPanel.mangedByLabel.setText("MANAGED BY: " + tempString.trim());	
						}
					}
					else 
					{
						taskPanel.mangedByLabel.setText("MANAGED BY: ");
					}
					
					if (assignedToByAttributeBox.isSelected())
					{
						tempString = rs.getString(--count);
						if(tempString != null)
						{
							taskPanel.assignedToLabel.setText("ASSIGNED TO: " + tempString.trim());	
						}
					}
					else 
					{
						taskPanel.assignedToLabel.setText("ASSIGNED TO: ");
					}
					
					if (priorityAttributeBox.isSelected())
					{
						tempString = rs.getString(--count);
						if(tempString != null)
						{
							taskPanel.priorityLabel.setText("PRIORITY: " + tempString.trim());	
						}
					}
					else 
					{
						taskPanel.priorityLabel.setText("PRIORITY: ");
					}
					
					if (completedAttributeBox.isSelected())
					{
						Timestamp tempTimestamp = rs.getTimestamp(--count);
						if(tempTimestamp != null)
						{
							taskPanel.completedDate.setText("COMPLETED: " + tempTimestamp.toString().substring(0, tempTimestamp.toString().lastIndexOf(":")));	
						}
					}
					else 
					{
						taskPanel.completedDate.setText("COMPLETED: ");
					}
					
					if (estimatedAttributeBox.isSelected())
					{
						Timestamp tempTimestamp = rs.getTimestamp(--count);
						if(tempTimestamp != null)
						{
							taskPanel.estimatedDate.setText("ESTIMATED: " + tempTimestamp.toString().substring(0, tempTimestamp.toString().lastIndexOf(":")));	
						}
					}
					else 
					{
						taskPanel.estimatedDate.setText("ESTIMATED: ");
					}
					
					if (createdOntributeBox.isSelected())
					{
						Timestamp tempTimestamp = rs.getTimestamp(--count);
						if(tempTimestamp != null)
						{
							taskPanel.createdDate.setText("CREATED: " + tempTimestamp.toString().substring(0, tempTimestamp.toString().lastIndexOf(":")));	
						}
					}
					else 
					{
						taskPanel.createdDate.setText("CREATED: ");
					}
					
					if (descriptionAttributeBox.isSelected())
					{
						tempString = rs.getString(--count);
						if(tempString != null)
						{
							taskPanel.descriptionArea.setText(tempString.trim());	
						}
					}
					else 
					{
						taskPanel.descriptionArea.setText("");
					}
					
					if (nameAttributeBox.isSelected())
					{
						tempString = rs.getString(--count);
						if(tempString != null)
						{
							taskPanel.title.setText(tempString.trim());	
						}
					}
					else 
					{
						taskPanel.title.setText("TITLE");
					}
				}
				s.close();
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void UserChanged()
	{
		System.out.println("USER CHANGED TASK LIST");
		currentSession = Startup.getSession();
		if(currentSession != null) {
			if (currentSession.getType() == TeamMemberType.QUALITY_ASSURANCE) {
				editTaskButton.setEnabled(true);
				addTaskButton.setEnabled(true);
				removeTaskButton.setEnabled(false);
				
			} else if (currentSession.getType() == TeamMemberType.CLIENT) {
				editTaskButton.setEnabled(true);
				addTaskButton.setEnabled(true);
				removeTaskButton.setEnabled(false);
			
			//	estimatedAttributeBox.setEnabled(false);
			} else if (currentSession.getType() == TeamMemberType.MANAGER) {
				editTaskButton.setEnabled(true);
				addTaskButton.setEnabled(false);
				removeTaskButton.setEnabled(true);
			//	estimatedAttributeBox.setEnabled(false);
			} else if (currentSession.getType() == TeamMemberType.DEVELOPER) {
				editTaskButton.setEnabled(true);
				addTaskButton.setEnabled(false);
				removeTaskButton.setEnabled(false);
			
			}
		}
	}
	
	public void clearLabels()
	{
		taskPanel.title.setText("TITLE");
		taskPanel.descriptionArea.setText("");
		taskPanel.createdDate.setText("CREATED: ");
		taskPanel.estimatedDate.setText("ESTIMATED: ");
		taskPanel.completedDate.setText("COMPLETED: ");
		taskPanel.priorityLabel.setText("PRIORITY: ");
		taskPanel.mangedByLabel.setText("MANAGED BY: ");
		taskPanel.createdByLabel.setText("CREATED BY: ");
		taskPanel.assignedToLabel.setText("ASSIGNED TO: ");
		
	}

}
