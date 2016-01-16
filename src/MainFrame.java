import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	private StopWatchPanel stopWatchPanel;
	private TextPanel textPanel;

	public MainFrame() {

		stopWatchPanel = new StopWatchPanel();
		textPanel = new TextPanel();

		setLayout(new BorderLayout());
		add(stopWatchPanel, BorderLayout.WEST);
		add(textPanel, BorderLayout.CENTER);

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
