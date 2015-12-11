package model;

import java.util.Comparator;

public class Reply implements Comparator<Reply>, Comparable<Reply>{
	private int reply_id;
	private int topic_id;
	private int reply_to_reply_id;
	private int author_id;
	private String content;
	private String posted_at;
	private int popularity;
	
	public Reply(){};
	
	public Reply(int reply_id, int topic_id, int reply_to_reply_id, int author_id, String content, String posted_at) {
		super();
		this.reply_id = reply_id;
		this.topic_id = topic_id;
		this.reply_to_reply_id = reply_to_reply_id;
		this.author_id = author_id;
		this.content = content;
		this.posted_at = posted_at;
		
	}
	
	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	public int getReply_id() {
		return reply_id;
	}
	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}
	public int getTopic_id() {
		return topic_id;
	}
	public void setTopic_id(int topic_id) {
		this.topic_id = topic_id;
	}
	public int getReply_to_reply_id() {
		return reply_to_reply_id;
	}
	public void setReply_to_reply_id(int reply_to_reply_id) {
		this.reply_to_reply_id = reply_to_reply_id;
	}
	public int getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPosted_at() {
		return posted_at;
	}
	public void setPosted_at(String posted_at) {
		this.posted_at = posted_at;
	}

 // Overriding the compareTo method
   public int compareTo(Reply r){
      return (r.popularity-this.popularity);
   }

   // Overriding the compare method to sort the age 
   public int compare(Reply r, Reply r1){
      int comparePop =  r1.getPopularity(); 	
        return comparePop-this.popularity;
   }
	
		
		
	
	
	
}
