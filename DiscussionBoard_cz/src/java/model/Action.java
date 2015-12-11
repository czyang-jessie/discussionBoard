package model;

public class Action {
	
	private int action_id;
	private int topic_id;
	private int reply_id;
	private int user_id;
	private String type;
	private String action_date;
	
	public Action(){}

	public Action(int action_id, int topic_id, int reply_id, int user_id, String type, String action_date) {
		super();
		this.action_id = action_id;
		this.topic_id = topic_id;
		this.reply_id = reply_id;
		this.user_id = user_id;
		this.type = type;
		this.action_date = action_date;
	}

	public int getAction_id() {
		return action_id;
	}

	public void setAction_id(int action_id) {
		this.action_id = action_id;
	}

	public int getTopic_id() {
		return topic_id;
	}

	public void setTopic_id(int topic_id) {
		this.topic_id = topic_id;
	}

	public int getReply_id() {
		return reply_id;
	}

	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAction_date() {
		return action_date;
	}

	public void setAction_date(String action_date) {
		this.action_date = action_date;
	};
	
	
}
