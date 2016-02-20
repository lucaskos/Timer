package gui;
import java.util.EventObject;

public class TableEvent extends EventObject {

	private String title;
	private String description;
	private int time;
	
	public TableEvent(Object source, String title, String description, int time ){
		super(source);
		this.title = title;
		this.description = description;
		this.time = time;
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


	public String toString(){
		return getTitle() + " " + getDescription() + " " + getTime();
	}
}
