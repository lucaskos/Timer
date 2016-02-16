import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ActivityTableModel extends AbstractTableModel {

	private List<Activity> db;

	private String[] colNames = { "ID", "Title", "Description", "Time" };

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public int getRowCount() {
		return 0;
//		return db.size();
	}
	
	public void setData(List<Activity> db) {
		this.db = db;
	}

	@Override
	public Object getValueAt(int row, int col) {
		Activity activity = db.get(row);

		switch (col) {
		case 0:
			return activity.getId();
		case 1:
			return activity.getTitle();
		case 2:
			return activity.getDescription();
		case 3:
			return activity.getTime();
		}
		return null;
	}
}
