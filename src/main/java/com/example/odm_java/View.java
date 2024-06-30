package com.example.odm_java;

public enum View {
	MEMBRE("Member.fxml"),
	PLANNING("Planning.fxml"),
	ADD_MEMBRE("AddMember.fxml");

	private String filename;

	View(String filename){
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

}

