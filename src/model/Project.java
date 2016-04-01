package model;

import java.util.LinkedList;

public class Project {
	private String name;
	private static int count = 0;
	private int id;
	LinkedList<Activity> activities;

	public Project(String name, LinkedList<Activity> activities) {

		this.name = name;
		this.id = count;
		this.activities = activities;

		count++;
	}

	public LinkedList<Activity> getActivities() {
		return activities;
	}

	public void setProjectName(String name) {
		this.name = name;
	}

	public String getProjectName() {
		// must return anArray of all projects????
		return name;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}

}
