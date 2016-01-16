import java.awt.BorderLayout;
import java.awt.Event;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	private StopWatchPanel stopWatchPanel;
	private TextPanel textPanel;
	private Table table;

	public MainFrame() {

		stopWatchPanel = new StopWatchPanel();
		textPanel = new TextPanel();
		table = new Table();

		setLayout(new BorderLayout());
		add(stopWatchPanel, BorderLayout.WEST);
		add(textPanel, BorderLayout.CENTER);
		add(table, BorderLayout.SOUTH);

		stopWatchPanel.setWatchPanelListener(new DateListener() {
			public void appendText(String time) {
				textPanel.appendText(time);
			}

		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setVisible(true);
	}

}
