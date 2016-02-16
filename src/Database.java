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

	public void addActivity(Activity Activity) {
		activities.add(Activity);
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void saveToFile(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		Activity[] Activitys = activities.toArray(new Activity[activities.size()]);

		oos.writeObject(Activitys);

		oos.close();
	}

	public void loadFromFile(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		try {
			Activity[] Activitys = (Activity[]) ois.readObject();
			activities.clear();

			activities.addAll(Arrays.asList(Activitys));

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		ois.close();
	}
}
