import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame {
	private StopWatchPanel stopWatchPanel;
	private TextPanel textPanel;
	
	private TablePanel tablePanel;

	public MainFrame() {

		stopWatchPanel = new StopWatchPanel();
		textPanel = new TextPanel();
		tablePanel = new TablePanel();
		
		setLayout(new BorderLayout());
		add(stopWatchPanel, BorderLayout.WEST);
		add(new JScrollPane(tablePanel), BorderLayout.CENTER);
		//add(textPanel, BorderLayout.CENTER);

		stopWatchPanel.setWatchPanelListener(new DateListener() {
//			public void appendText(String time) {
//				textPanel.appendText(time);
//			}

			@Override
			public void appendTable(String title, String description, int time) {
				tablePanel.appendTable(title, description, time);
				
			}

		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setVisible(true);
	}

}
