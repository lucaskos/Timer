import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuBar extends JMenuBar {

	private JMenu mainItem;
	private JMenuItem exportItem;
	private JMenuItem importItem;
	private JFileChooser fc;
	private JMenuItem exitItem;

	MenuBar() {

		fc = new JFileChooser();
		// Filter show only extension given in the class Utils inside class
		// Filter
		fc.addChoosableFileFilter(new FFilter());

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
				// fc.showSaveDialog(MenuBar.this);
				int results = fc.showSaveDialog(MenuBar.this);
				if (results == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					System.out.println(fc.getSelectedFile());
//					InputStream in;
//					try {
//						in = new FileInputStream(file);
//						OutputStream fos = new FileOutputStream(file);
//						if (!file.exists()) {
//							file.createNewFile();
//						}
//						fos.write();
//						fos.close();
//					} catch (FileNotFoundException e1) {
//						System.out.println("File not found");
//					} catch (IOException e1) {
//						e1.printStackTrace();
//					}

				} else { // cancelling option
					// if(results == JFileChooser.CANCEL_OPTION) {
					System.out.println("saving canceled");
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
