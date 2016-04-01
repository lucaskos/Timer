package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class StopWatchPanel extends JPanel {

	private static final String Start = "Start";
	private static final String Stop = "Save";
	private static final String Reset = "Reset";
	private static final String Pause = "Pause";
	private static final String Resume = "Resume";

	private TextLabel textLabel;
	private JButton startBtn;
	private JButton resetBtn;
	private JButton pauseBtn;
	private JButton stopBtn;
	private JTextField titleField;
	private JTextArea descrField;
	private JLabel titleLabel;
	private JLabel descrLabel;
	private TableListener tableListener;
	
	final String labels[] = { "A", "B", "C", "D", "E", "F", "G" };
	private JLabel projectLabel;
	private JComboBox<String> projectList;
	private DefaultComboBoxModel<String> listModel;
	private JScrollPane scrollList;
	private JButton projectOkBtn;
	
	private JPanel jp;

	public StopWatchPanel() {
		int size = 20;
		Dimension dim = getPreferredSize();
		dim.width = 450;
		setPreferredSize(dim);
		titleLabel = new JLabel("Title: ");
		startBtn = new JButton(Start);
		resetBtn = new JButton(Reset);
		pauseBtn = new JButton(Pause);

		stopBtn = new JButton(Stop);
		stopBtn.setEnabled(false);

		textLabel = new TextLabel();
		titleField = new JTextField(size);
		descrField = new JTextArea();

		descrLabel = new JLabel("Description");

		descrField.setColumns(size);
		descrField.setWrapStyleWord(false);
		descrField.setLineWrap(false);
		descrField.setRows(4);
		descrField.setVisible(true);

		projectOkBtn = new JButton("Add Project");
		listModel = new DefaultComboBoxModel<>(labels);
		projectLabel = new JLabel("Projects");
		projectList = new JComboBox<String>(listModel);
		scrollList = new JScrollPane(projectList);
		
		makeLayout();
		defaultButtonsState();

		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkingTextFields() != false) {
					textFieldErrorPane();
				} else {
					textLabel.start();
					pauseBtn.setEnabled(true);
					stopBtn.setEnabled(true);
					startBtn.setEnabled(false);
				}
				/*
				 * poprawic wyswietlanie w minutach i godzinach i sekundach
				 * 
				 * private String checkTime(int time) { if(time > 60) { return
				 * new SimpleDateFormat("mm:ss").format(time); } else if (time >
				 * 3600) { return new SimpleDateFormat("HH:mm:ss").format(time);
				 * } else { return "" +time; }
				 * 
				 * 
				 * }
				 */
			}
		});
		// saving data
		stopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titleFieldText = titleField.getText();
				String descrFieldFieldText = descrField.getText();
				textLabel.pause();
				int time = textLabel.getSeconds();
				textLabel.reset();
				System.out.println(time + "\n");
				TableEvent ev = new TableEvent(this, titleFieldText,
						descrFieldFieldText, time);
				if (tableListener != null) {
					tableListener.formEventOccurred(ev);
					startBtn.setEnabled(true);
					stopBtn.setEnabled(false);
				}
				// after the object is saved
				titleField.setText(null);
				titleField.requestFocus();
				descrField.setText(null);

			}
		});
		// reseting timer
		// setting timeLabel to 0 (zero)
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textLabel.reset();
				startBtn.setEnabled(true);
				titleField.requestFocus();
				stopBtn.setEnabled(false);

			}
		});
		// pause/resume button
		pauseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				if (cmd == Pause) {
					textLabel.pause();
					pauseBtn.setText(Resume);
				} else {
					textLabel.start();
					pauseBtn.setText(Pause);
				}

			}
		});
	}

	private void makeLayout() {
		Border border = BorderFactory.createEtchedBorder();
		setBorder(border);

		JScrollPane areaScrollPane = new JScrollPane(descrField);
		areaScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagLayout gbl = new GridBagLayout();

		GridBagConstraints gbc = new GridBagConstraints();
		jp = new JPanel();
		jp.setLayout(gbl);

		gbc.insets = new Insets(20, 5, 5, 5);
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;

		// first label

		gbc.gridy = 0;
		gbc.gridx = 0;
		gbl.setConstraints(projectLabel, gbc);
		jp.add(projectLabel);
		
		gbc.gridy++;
		gbc.gridx = 0;
		gbl.setConstraints(scrollList, gbc);
		jp.add(scrollList);
		
		gbc.gridx = 1;
		gbl.setConstraints(projectOkBtn, gbc);
		jp.add(projectOkBtn);
		
		gbc.gridy++;
		gbc.gridx = 0;
		gbl.setConstraints(titleLabel, gbc);
		jp.add(titleLabel);

		gbc.gridx = 1;
		gbl.setConstraints(titleField, gbc);
		jp.add(titleField);

		gbc.insets = new Insets(0, 5, 5, 5);
		gbc.gridy++;
		gbc.gridx = 0;
		gbl.setConstraints(descrLabel, gbc);
		jp.add(descrLabel);

		gbc.gridx = 1;
		gbl.setConstraints(areaScrollPane, gbc);
		jp.add(areaScrollPane);

		gbc.insets = new Insets(20, 5, 5, 5);
		gbc.anchor = GridBagConstraints.BELOW_BASELINE;
		gbc.gridy++;
		gbc.gridx = 0;
		gbl.setConstraints(resetBtn, gbc);
		jp.add(resetBtn);

		gbc.gridx = 1;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbl.setConstraints(textLabel, gbc);
		jp.add(textLabel);

		gbc.gridy++;
		gbc.gridx = 0;
		gbl.setConstraints(startBtn, gbc);
		jp.add(startBtn);
		gbc.gridx = 1;

		gbl.setConstraints(pauseBtn, gbc);
		jp.add(pauseBtn);

		gbc.gridy++;
		gbc.gridx = 0;
		gbl.setConstraints(stopBtn, gbc);
		jp.add(stopBtn);

		add(jp);

	}

	public void defaultButtonsState() {
		startBtn.setEnabled(true);
		pauseBtn.setEnabled(false);
		stopBtn.setEnabled(false);
	}

	public boolean checkingTextFields() {
		boolean error = false;
		if (titleField.getText().isEmpty() || descrField.getText().isEmpty()) {
			error = true;
		}
		return error;
	}

	public void textFieldErrorPane() {
		JOptionPane.showMessageDialog(null, "Both fields must be entered");
		if (titleField.getText().isEmpty()) {
			titleField.requestFocus();
		} else {
			descrField.requestFocus();
		}
	}

	public void setWatchPanelListener(TableListener tableListener) {
		this.tableListener = tableListener;
	}
}