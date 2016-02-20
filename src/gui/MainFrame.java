package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import controller.Controller;

public class MainFrame extends JFrame {
	private StopWatchPanel stopWatchPanel;
	private TablePanel tablePanel;
	private JFileChooser fileChooser;
	private Controller controller;
	private FFilter fileFilter;

	public MainFrame() {

		controller = new Controller();

		stopWatchPanel = new StopWatchPanel();
		tablePanel = new TablePanel();

		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(fileFilter);

		// MenuBar function inside MainFrame to have easy access to MainFrame,
		// tablePanel, controller etc.
		setJMenuBar(createMenuBar());

		// Crucial to pass values of the database to the TablePanel
		// Without, it shows an error
		tablePanel.setData(controller.getActivities());

		setLayout(new BorderLayout());

		stopWatchPanel.setWatchPanelListener(new TableListener() {
			public void formEventOccurred(TableEvent e) {
				controller.addActivity(e);
				tablePanel.refresh();
			}

		});

		add(stopWatchPanel, BorderLayout.WEST);
		add(new JScrollPane(tablePanel), BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setVisible(true);
	}

	private JMenuBar createMenuBar() {

		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenuItem exportItem = new JMenuItem("Export...");
		JMenuItem importItem = new JMenuItem("Import...");
		JMenuItem exitItem = new JMenuItem("Exit");

		fileFilter = new FFilter();

		fileMenu.add(exportItem);
		fileMenu.add(importItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);

		// Setting up mnemonics
		exportItem.setMnemonic(KeyEvent.VK_E);
		importItem.setMnemonic(KeyEvent.VK_I);
		exitItem.setMnemonic(KeyEvent.VK_X);
		fileMenu.setMnemonic(KeyEvent.VK_F);

		menuBar.add(fileMenu);

		// Setting up accelerator
		// CTRL+X is not working casue is taken on linux
		exportItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		importItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));

		/*
		 * file opener extensions are not shown up. check getDescription
		 */
		importItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.loadFromFile(fileChooser.getSelectedFile());
						tablePanel.refresh();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(MainFrame.this, ex, "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}); // the end of file import

		/*
		 * I think to save a Java component one needs to use serialization
		 * (JPanel implements serializable) Need to pass it through
		 * constructor(and or Data) to save information from JTable It shows
		 * prompt to create a file but doesn't create one
		 * 
		 */
		exportItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.saveToFile(fileChooser.getSelectedFile());

					} catch (IOException ex) {
						JOptionPane.showMessageDialog(MainFrame.this, ex, "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}); // the end of saving item in the menu

		// exit item
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);

			}
		});

		return menuBar;
	}

}
