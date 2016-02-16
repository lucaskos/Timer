import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame {
	private StopWatchPanel stopWatchPanel;
	private MenuBar menuBar;
	private TablePanel tablePanel;
	
	private Controller controller;

	public MainFrame() {

		controller = new Controller();
		
		stopWatchPanel = new StopWatchPanel();
		new TextPanel();
		tablePanel = new TablePanel();
		menuBar = new MenuBar();
		
		
		setLayout(new BorderLayout());
		add(stopWatchPanel, BorderLayout.WEST);
		add(new JScrollPane(tablePanel), BorderLayout.CENTER);
		add(menuBar, BorderLayout.NORTH);
//		stopWatchPanel.setWatchPanelListener(new DateListener() {
//
//			@Override
//			public void appendTable(String title, String description, int time) {
//				tablePanel.appendTable(title, description, time);
//				
//			}
//
//		});

		stopWatchPanel.setWatchPanelListener(new TableListener() {
			public void formEventOccurred(TableEvent e) {
				controller.addActivity(e);
				tablePanel.refresh();
			}

		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setVisible(true);
	}

}
