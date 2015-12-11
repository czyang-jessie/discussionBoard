package model;

public class Topic {
	private int topic_id;
	private int author_id;
	private int project_id;
	private String title;
	private String content;
	private String posted_at;
	private long popularity;
	
	public Topic(){};
	
	public Topic(int topic_id, int author_id, int project_id, String title, String content,String posted_at) {
		
		this.topic_id = topic_id;
		this.author_id = author_id;
		this.project_id = project_id;
		this.title = title;
		this.content = content;
		this.posted_at = posted_at;
	
	}
	public long getPopularity() {
		return popularity;
	}

	public void setPopularity(long popularity) {
		this.popularity = popularity;
	}

	public String getPosted_at() {
		return posted_at;
	}
	public void setPosted_at(String posted_at) {
		this.posted_at = posted_at;
	}
	public int getTopic_id() {
		return topic_id;
	}
	public void setTopic_id(int topic_id) {
		this.topic_id = topic_id;
	}
	public int getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
