import java.util.ArrayList;
import java.util.List;

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
	
	

}
