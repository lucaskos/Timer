package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import controller.Controller;

public class MenuBar extends JMenuBar {

	private JMenu mainItem;
	private JMenuItem exportItem;
	private JMenuItem importItem;
	private JFileChooser fc;
	private JMenuItem exitItem;
	private Controller controller;

	MenuBar() {

		fc = new JFileChooser();
		// Filter show only extension given in the class Utils inside class
		// Filter
		fc.addChoosableFileFilter(new FFilter());
		controller = new Controller();
		
		
		mainItem = new JMenu("File");
		exportItem = new JMenuItem("Export...");
		importItem = new JMenuItem("Import...");
		exitItem = new JMenuItem("Exit");

		/*
		 * file opener extensions are not shown up. check getDescription
		 */
		importItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int retVal = fc.showOpenDialog(MenuBar.this);
				if (retVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					System.out.println(file.getName());
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
				if (fc.showSaveDialog(MenuBar.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.saveToFile(fc.getSelectedFile());

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
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

		// Setting up mnemonics
		exportItem.setMnemonic(KeyEvent.VK_E);
		importItem.setMnemonic(KeyEvent.VK_I);
		exitItem.setMnemonic(KeyEvent.VK_X);
		mainItem.setMnemonic(KeyEvent.VK_F);

		// Setting up accelerator
		// CTRL+X is not working casue is taken on linux
		exportItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		importItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));

		mainItem.add(exportItem);
		mainItem.add(importItem);
		mainItem.addSeparator();
		mainItem.add(exitItem);
		add(mainItem);

	}

}
