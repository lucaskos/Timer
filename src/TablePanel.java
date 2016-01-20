import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class TablePanel extends JTable {
	private DefaultTableModel model;
	private static int rowCount;
	String[] colNames = {"Activity", "Description", "Time"};

	TablePanel() {
		model = new DefaultTableModel(colNames, 0);
		setEnabled(false);
		setModel(model);
		
		
		model.insertRow(0, new Object[] {"22", "aa", 2});
	}
	public void appendRow(String[] list) {
		
	}
	public void appendTable(String title, String description, int time) {
		model.insertRow(0, new Object[] {title, description, time});
		
	}
}
