package ui;

import java.awt.*;

import javax.swing.*;

import connection.Session;

public class Startup extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3909146540187316216L;
	private final static String LOOKANDFEEL = null;
	private JPanel mainPane;
	private ProjectList projectListPanel;
	private TaskList taskListPanel;
	private TaskPanel taskPanel;
	private LoginDialog loginDialog;
	private Session currentSession;

	public Startup(String name) {
		super(name);
		GridBagConstraints gbc = new GridBagConstraints();
		
		// Set the look and feel.
		initLookAndFeel();

		// Create and set up the window.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Create login dialog
		loginDialog = new LoginDialog(this);
		
		// Create and set up the content pane.
		mainPane = new JPanel();
		mainPane.setOpaque(true); // content panes must be opaque
		setContentPane(mainPane);
		mainPane.setBorder(BorderFactory.createLineBorder(Color.black));
		
		setJMenuBar(new ui.MenuBar());
		setPreferredSize(new Dimension(1200, 800));
		
		// Set layout
		setLayout(new GridBagLayout());
		
		// Add panels
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.1;
		gbc.weighty = 1;
		projectListPanel = new ProjectList();
		add(projectListPanel, gbc);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0.1;
		gbc.weighty = 1;
		taskListPanel = new TaskList();
		add(taskListPanel, gbc);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.weightx = 0.8;
		gbc.weighty = 1;
		taskPanel = new TaskPanel();
		add(taskPanel, gbc);
		
		// Start maximized
		setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH );
		
		// Display the window.
		pack();
		setVisible(true);
		
		// Display login dialog
		loginDialog.setVisible(true);
		
		// Start current session as null
		currentSession = null;
	}

	public static void main(String args[]) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Startup("The Dream Tasker");
			}
		});
	}


	private static void initLookAndFeel() {
		String lookAndFeel = null;

		if (LOOKANDFEEL != null) {
			if (LOOKANDFEEL.equals("Metal")) {
				lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
			} else if (LOOKANDFEEL.equals("System")) {
				lookAndFeel = UIManager.getSystemLookAndFeelClassName();
			} else if (LOOKANDFEEL.equals("Motif")) {
				lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
			} else if (LOOKANDFEEL.equals("GTK+")) { // new in 1.4.2
				lookAndFeel = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
			} else {
				System.err
						.println("Unexpected value of LOOKANDFEEL specified: "
								+ LOOKANDFEEL);
				lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
			}

			try {
				UIManager.setLookAndFeel(lookAndFeel);
			} catch (ClassNotFoundException e) {
				System.err
						.println("Couldn't find class for specified look and feel:"
								+ lookAndFeel);
				System.err
						.println("Did you include the L&F library in the class path?");
				System.err.println("Using the default look and feel.");
			} catch (UnsupportedLookAndFeelException e) {
				System.err.println("Can't use the specified look and feel ("
						+ lookAndFeel + ") on this platform.");
				System.err.println("Using the default look and feel.");
			} catch (Exception e) {
				System.err.println("Couldn't get specified look and feel ("
						+ lookAndFeel + "), for some reason.");
				System.err.println("Using the default look and feel.");
				e.printStackTrace();
			}
		}

	}
}
