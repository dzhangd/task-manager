package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import database.Project;
import database.Task;
import ui.ProjectList.ProjectListListener;

import static ui.ProjectList.currentPid;
import static ui.ProjectList.newTaskList;
import static ui.Startup.taskPanel;


public class TaskList extends JPanel {

	static DefaultListModel listModel = new DefaultListModel();
	Task task = new Task();
	ArrayList<Object[]> tasks = task.getTasks();

	public TaskList()
	{

		GridBagConstraints gbc = new GridBagConstraints();
		this.setBackground(Color.CYAN);

		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(100, 0));
		
		final JList taskJList = new JList(listModel);
		final ListSelectionModel listSelectionModel = taskJList.getSelectionModel();
		listSelectionModel.addListSelectionListener(new TaskListListener());
		taskJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane taskScrollPane = new JScrollPane(taskJList);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 0.85;
		this.add(taskScrollPane, gbc);

		JButton editTaskButton = new JButton("Edit Task");
		JButton addTaskButton = new JButton("Add Task");
		JButton removeTaskButton = new JButton("Remove Task");

		editTaskButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Edit Task Button Clicked");
				JTextField newTaskName = new JTextField();
				JTextField newTaskDescription = new JTextField();
				Object[] type = {"Bug", "Feature"};
				JComboBox newTypeCombo = new JComboBox(type);
				Object[] priority = {"1", "2", "3", "4", "5"};
				JComboBox newPriorityCombo = new JComboBox(priority);
				Object[] message = {
						"Task name:", newTaskName,
						"Task Description:", newTaskDescription,
						"Type:", newTypeCombo,
						"Priority:", newPriorityCombo
				};
				int editTaskSelection = JOptionPane.showConfirmDialog(null, message, "Edit Task", JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION);
				if(editTaskSelection != 0)
				{
					return;
				}
				System.out.println("Task name is " + newTaskName.getText() + " Task description is " + newTaskDescription.getText() + " type index is " + newTypeCombo.getSelectedIndex() + " Priority index is " + newPriorityCombo.getSelectedIndex());

				// TODO: change stuff when users are available, but for now:
				int tid = (Integer) newTaskList.get(selectedIndex)[0];
				System.out.println("Task tid to edit is " + tid);
				task.editTask(tid, newTaskName.getText(), newTaskDescription.getText(), newPriorityCombo.getSelectedIndex());
				listModel.set(selectedIndex,newTaskName.getText());
				taskPanel.title.setText(newTaskName.getText());
				taskPanel.descriptionArea.setText(newTaskDescription.getText());

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

				// TODO: change later when available!
				System.out.println("Task name is " + taskName.getText() + " Task description is " + taskDescription.getText() + " type index is " + typeCombo.getSelectedIndex() + " Priority index is " + priorityCombo.getSelectedIndex());

				int tid = task.getMaxTid() + 1;
				System.out.println("tid is " + tid);
				int pid = currentPid;
				java.sql.Date subDate = new java.sql.Date(new java.util.Date().getTime());
				java.sql.Date comDate = new java.sql.Date(new java.util.Date().getTime());
				task.addTask(tid, taskName.getText(), taskDescription.getText(), subDate, comDate, false, priorityCombo.getSelectedIndex(), 1031, 1033, pid);
				String title = (String) tasks.get(tasks.size()-1)[1];
				listModel.addElement(title.trim());
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
				int tid = (Integer) newTaskList.get(selectedIndex)[0];
				task.deleteTask(tid);
				listModel.remove(selectedIndex);
			}
		});


		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 0.05;
		this.add(editTaskButton, gbc);
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

	private int selectedIndex;
	ListSelectionModel lsm;

	class TaskListListener implements ListSelectionListener
	{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				return;
			}
			lsm = (ListSelectionModel)e.getSource();
			int firstIndex = e.getFirstIndex();
			int lastIndex = e.getLastIndex();
			selectedIndex = lsm.getLeadSelectionIndex();
			System.out.println("Task selected is: " + selectedIndex);

			if (!newTaskList.contains(tasks.get(tasks.size() - 1))) {
				newTaskList.add(tasks.get(tasks.size() - 1));
			}

			if (taskPanel != null && selectedIndex >= 0) {
				String title = (String) newTaskList.get(selectedIndex)[1];
				taskPanel.title.setText(title.trim());

				String description = (String) newTaskList.get(selectedIndex)[2];
				description = description.replace("’","\\'");
				taskPanel.descriptionArea.setText(description.trim());
				System.out.println("description is: " + description);

				// TODO: CHANGE USER, ASSIGNED TO, MANAGER WHEN AVAILABLE

				Date subDate = (Date) newTaskList.get(selectedIndex)[3];
				taskPanel.createdDate.setText("CREATED: " + String.valueOf(subDate));

				Date comDate = (Date) newTaskList.get(selectedIndex)[4];
				taskPanel.estimatedDate.setText("ESTIMATED: " + String.valueOf(comDate));


			}
		}

	}

}