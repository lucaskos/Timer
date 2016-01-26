import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class StopWatchPanel extends JPanel {

	private static final String Start = "Start";
	private static final String Stop = "Stop";
	private static final String Reset = "Reset";

	private TextLabel textLabel;
	private JButton button;
	private JButton resetBtn;
	private JTextField title;
	private JTextArea description;
	private JLabel titleLabel;
	private JLabel descrLabel;
	private DateListener dateListener;

	private Controller controller;

	public StopWatchPanel() {
		int size = 20;
		Dimension dim = getPreferredSize();
		dim.width = 400;
		setPreferredSize(dim);

		button = new JButton(Start);
		resetBtn = new JButton(Reset);
		textLabel = new TextLabel();
		title = new JTextField(size);
		description = new JTextArea();
		titleLabel = new JLabel("Title: ");
		descrLabel = new JLabel("Description: ");

		description.setColumns(size);
		description.setWrapStyleWord(false);
		description.setLineWrap(false);
		description.setRows(4);
		description.setVisible(true);

		makeLayout();

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();

				// create a method to validate title and description
				// trim, firstToUpperCase

				if (title.getText().trim().isEmpty() || description.getText().trim().isEmpty()) {
					System.out.println("enter text");
				} else {
					if (Stop.equals(cmd)) {
						textLabel.pause();
						button.setText(Start);
						// System.out.println("Stop: " +
						// textLabel.getTimeOfActionEnd() + "\n\n");
						int time = textLabel.getSeconds();
						// dateListener.appendText("CZAS: " + time + "\n");

						// Making call to other cell in the GUI, to append
						// JTable
						// Has to be made either through controller or through
						// listener
						dateListener.appendTable(title.getText().trim(), description.getText().trim(), time);

						// Controller to pass it to Database in the future
						controller = new Controller(title.getText(), description.getText(), time);

					} else {
						textLabel.start();
						// System.out.println("Start: " +
						// textLabel.getTimeOfActionStart());
						button.setText(Stop);
					}
				}
			}
			/*
			 * poprawic wyswietlanie w minutach i godzinach i sekundach
			 * 
			 * private String checkTime(int time) { if(time > 60) { return new
			 * SimpleDateFormat("mm:ss").format(time); } else if (time > 3600) {
			 * return new SimpleDateFormat("HH:mm:ss").format(time); } else {
			 * return "" +time; }
			 * 
			 * 
			 * }
			 */
		});
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				if (Reset.equals(cmd))
					textLabel.reset();
			}
		});
	}

	private void makeLayout() {
		Border border = BorderFactory.createEtchedBorder();
		setBorder(border);

		JScrollPane areaScrollPane = new JScrollPane(description);
		areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridy = 0;
		gbc.gridx = 0;

		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(titleLabel, gbc);
		gbc.gridx = 1;
		add(title, gbc);

		gbc.gridy = 1;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(descrLabel, gbc);
		gbc.gridx = 1;
		gbc.weightx = 0.1;
		// gbc.weighty = 0.2;
		add(areaScrollPane, gbc);

		gbc.gridy++;
		gbc.gridx = 0;
		gbc.weightx = 3;
		gbc.gridwidth = 5;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(resetBtn, gbc);

		gbc.gridy++;
		gbc.gridx = 0;
		gbc.gridwidth = 4;
		gbc.anchor = GridBagConstraints.NORTHEAST;
		add(textLabel, gbc);

		gbc.gridy++;
		gbc.gridx = 0;
		gbc.gridwidth = 5;
		gbc.anchor = GridBagConstraints.NORTHEAST;
		add(button, gbc);

	}

	public void setWatchPanelListener(DateListener dateListener) {
		this.dateListener = dateListener;
	}
}