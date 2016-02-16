import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TablePanel extends JTable {
	private DefaultTableModel model;
	
	private ActivityTableModel at_model;
	String[] colNames = {"Activity", "Description", "Time"};

	TablePanel() {
		model = new DefaultTableModel(colNames, 0);
		
		at_model = new ActivityTableModel();
		
		//what is this?
		//setEnabled(false);
		setModel(at_model);
		
	}
	
	public void appendTable(String title, String description, int time) {
		model.insertRow(0, new Object[] {title, description, time});
		
	}
	public void refresh() {
		at_model.fireTableDataChanged();		
	}
}
