package com.example.odm_java;

public class Member {
	private Number ID;
	private String ROLE;
	private String EMAIL;
	private String FIRST_NAME;
	private String LAST_NAME;

	public Member(Number ID, String ROLE, String EMAIL, String FIRST_NAME, String LAST_NAME) {
		this.ID = ID;
		this.ROLE = ROLE;
		this.EMAIL = EMAIL;
		this.FIRST_NAME = FIRST_NAME;
		this.LAST_NAME = LAST_NAME;
	}

	public Number getID() {
		return ID;
	}

	public void setID(Number ID) {
		this.ID = ID;
	}

	public String getROLE() {
		return ROLE;
	}

	public void setROLE(String ROLE) {
		this.ROLE = ROLE;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String EMAIL) {
		this.EMAIL = EMAIL;
	}

	public String getFIRST_NAME() {
		return FIRST_NAME;
	}

	public void setFIRST_NAME(String FIRST_NAME) {
		this.FIRST_NAME = FIRST_NAME;
	}

	public String getLAST_NAME() {
		return LAST_NAME;
	}

	public void setLAST_NAME(String LAST_NAME) {
		this.LAST_NAME = LAST_NAME;
	}
}
