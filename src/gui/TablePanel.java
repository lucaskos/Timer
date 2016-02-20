package gui;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Activity;

public class TablePanel extends JTable {
	
	private ActivityTableModel at_model;

	TablePanel() {
		
		at_model = new ActivityTableModel();
		
		//what is this?
		setEnabled(false);
		setModel(at_model);
	
	}
	
	public void setData(List<Activity> db){
		at_model.setData(db);
	}
	public void refresh() {
		at_model.fireTableDataChanged();		
	}
}
