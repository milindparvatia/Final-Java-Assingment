package model;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Post implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2110010630806366659L;

	private String id;
	private String title;
	private String description;
	private Student creatorID;
	// as boolean true mean status open when creating this post
	private boolean status;
	private ArrayList<Reply> replies;
	private String imageURL;

	public Post(String id, String title, String description, Student creatorID, boolean status, String imageURL) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.creatorID = creatorID;
		this.status = status;
		this.imageURL = imageURL;
		this.replies = new ArrayList<Reply>();
	}

	public Post() {
		// TODO Auto-generated constructor stub
	}

	public abstract boolean handleReply(Reply reply) throws InputErorrException;

	public abstract String getReplyDetails();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Student getCreatorID() {
		return creatorID;
	}

	public void setCreatorID(Student creatorID) {
		this.creatorID = creatorID;
	}

	public String getStatus() {
		if (status == true)
			return "OPEN";
		else if (status == false)
			return "CLOSE";
		return "Error";
	}

	public boolean getStatusForDB() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public ArrayList<Reply> getReplies() {
		return replies;
	}

	public void setReplies(ArrayList<Reply> replies) {
		this.replies = replies;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
}
