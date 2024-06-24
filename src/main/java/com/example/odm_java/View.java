package com.example.odm_java;

public enum View {
	MAIN("main.fxml"),
	MEMBRE("Membre.fxml"),
	PLANNING("Planning.fxml");

	private String filename;

	View(String filename){
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

}

