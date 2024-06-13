package com.odm.java_odm;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Welcome to JavaFX Plugin Project");
        VBox vbox = new VBox(label);
        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX Plugin Project");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
