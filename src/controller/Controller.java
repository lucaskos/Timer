package controller;
import java.io.File;
import java.io.IOException;
import java.util.List;

import gui.TableEvent;
import model.Activity;
import model.Database;

public class Controller {
	
	Database db = new Database();
		
	public List<Activity> getActivities(){
		return db.getActivities();
	}

	public void addActivity(TableEvent e) {
		String title = e.getTitle();
		String description = e.getDescription();
		int time = e.getTime();
		
		Activity activity = new Activity(title, description, time);
		db.addActivity(activity);
		
	}
	
	public void saveToFile(File file) throws IOException {
		db.saveToFile(file);
	}
	public void loadFromFile(File file) throws IOException {
		db.loadFromFile(file);
	}
}
