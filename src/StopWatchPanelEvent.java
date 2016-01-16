import java.util.EventObject;

public class StopWatchPanelEvent extends EventObject {

	private String title;
	private String description;
	private int time;

	public StopWatchPanelEvent(Object source, String title, String description, int time) {
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
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getTime() {
		return time;
	}
}
