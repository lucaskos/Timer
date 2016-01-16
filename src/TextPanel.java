import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class TextPanel extends JTextPane {
	private JTextArea textArea;
	
	public TextPanel() {
		textArea = new JTextArea();
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(textArea), BorderLayout.CENTER);
	}
	public void appendText(String time) {
		textArea.append(time);
	}
}
