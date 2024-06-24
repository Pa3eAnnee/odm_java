package com.example.odm_java;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class ViewSwitcher {
	public static Scene scene;

	public static void setScene(Scene scene) {
		ViewSwitcher.scene = scene;
	}

	public static void switchTo(View view) throws IOException {
		if (scene == null){
			System.out.println("Scene is null");
			return;
		}
		Parent root = FXMLLoader.load(Objects.requireNonNull(ViewSwitcher.class.getResource(view.getFilename())));
		scene.setRoot(root);
	}

}
