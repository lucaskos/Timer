import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Table extends JTable {

	private String[] columnNames = {"Title", "Description", "Time"};
	private DefaultTableModel model;
	
	
	public Table() {
		model = (DefaultTableModel) getModel();
		setVisible(true);
		setFillsViewportHeight(true);
		
		for(int i = 0; i < columnNames.length; i++) {
			model.addRow(new Object[] {columnNames[i]});
		}
		model.addRow(new Object[] {"aa", "bb", "CC"});
	}
	
}
