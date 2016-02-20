package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database {

	private ArrayList<Activity> activities;

	public Database() {
		activities = new ArrayList<Activity>();
	}

	public void addActivity(Activity Activities) {
		activities.add(Activities);
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void saveToFile(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		Activity[] Activities = activities.toArray(new Activity[activities.size()]);

		oos.writeObject(Activities);

		oos.close();
	}

	public void loadFromFile(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		try {
			Activity[] Activities = (Activity[]) ois.readObject();
			activities.clear();

			activities.addAll(Arrays.asList(Activities));

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		ois.close();
	}
}
