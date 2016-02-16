import java.io.Serializable;

public class Activity implements Serializable {

	private static final long serialVersionUID = 86186321831675684L;
	
	private static int count = 0;
	
	private int id;
	
	private String title;
	private String description;
	private int time;
	
	public Activity(String title, String description, int time) {
		super();
		this.title = title;
		this.description = description;
		this.time = time;
		
		this.id = count;
		count++;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public int getTime() {
		return time;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
