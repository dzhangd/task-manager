package ui;

import java.awt.*;

import javax.swing.*;

import connection.Session;

public class Startup {


	static JFrame frame;
	final static String LOOKANDFEEL = null;
	JPanel mainPane = new JPanel();
	static ProjectList projectListPanel;
	static TaskPanel taskPanel;
	static TaskList taskListPanel;
	static Session session;
	
	
	public Startup() {
		mainPane.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	public static void main(String args[]) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	protected static void createAndShowGUI() {
		
		// Hacky session solution until actual login
		// TODO Delete this later
		session = new Session("PaRappa the Rapper", 42, Session.UserType.superUser);
		
		projectListPanel = new ProjectList();
		taskListPanel = new TaskList();
		taskPanel = new TaskPanel();
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		// Set the look and feel.
		initLookAndFeel();

		// Create and set up the window.
		frame = new JFrame("The Dream Tasker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		Startup startup = new Startup();
		startup.mainPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(startup.mainPane);
		
		frame.setJMenuBar(new ui.MenuBar());
		frame.setPreferredSize(new Dimension(1200, 800));
		
		// Set layout
		frame.setLayout(new GridBagLayout());
		
		// Add panels
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.1;
		gbc.weighty = 1;
		frame.add(projectListPanel, gbc);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0.1;
		gbc.weighty = 1;
		frame.add(taskListPanel, gbc);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.weightx = 0.8;
		gbc.weighty = 1;
		frame.add(taskPanel, gbc);
		
		// Start maximized
		frame.setExtendedState( frame.getExtendedState()|JFrame.MAXIMIZED_BOTH );
		
		// Display the window.
		frame.pack();
		frame.setVisible(true);

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
