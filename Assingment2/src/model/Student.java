package model;

import java.io.Serializable;

public class Student implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8030562880005542874L;
	private String sid;

	public Student(String sid) {
		this.sid = sid;
	}

    public Student() {
    }

    public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

}
