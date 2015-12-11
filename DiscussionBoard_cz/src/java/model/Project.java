package model;

public class Project {
	private int project_id;
	private String project_title;
	private String project_description;
	
	public Project(){};
	
	public Project(int project_id, String project_title, String project_description) {
		this.project_id = project_id;
		this.project_title = project_title;
		this.project_description = project_description;
	}

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public String getProject_title() {
		return project_title;
	}

	public void setProject_title(String project_title) {
		this.project_title = project_title;
	}

	public String getProject_description() {
		return project_description;
	}

	public void setProject_description(String project_description) {
		this.project_description = project_description;
	}
	
}
