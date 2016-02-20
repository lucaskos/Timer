package gui;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Activity;

public class ActivityTableModel extends AbstractTableModel {

	private List<Activity> db;

	private String[] colNames = { "ID", "Title", "Description", "Time" };

	public ActivityTableModel() {
		System.out.println(this.getColumnName(1));
	}

	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}

	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	@Override
	public int getRowCount() {
		return db.size();
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
