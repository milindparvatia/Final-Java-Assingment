package model;

import java.io.Serializable;

public class Reply implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8142005554143904723L;
	private Post postID;
	private float value;
	private Student respondersId;
	private String studentId;

	public Reply(Post postID, float value, Student respondersId) {
		super();
		this.postID = postID;
		this.value = value;
		this.respondersId = respondersId;
		this.studentId = respondersId.getSid();
	}

	public Reply() {
		// TODO Auto-generated constructor stub
	}

	public Post getPostID() {
		return postID;
	}

	public void setPostID(Post postID) {
		this.postID = postID;
	}

	public float getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Student getRespondersId() {
		return respondersId;
	}

	public void setRespondersId(Student respondersId) {
		this.respondersId = respondersId;
	}

	public String getStudentId() {
		return studentId;
	}
}
