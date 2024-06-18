package com.example.odm_java;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Application extends javafx.application.Application {
    double x, y = 0;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader root = new FXMLLoader(Application.class.getResource("main.fxml"));
        Scene scene = new Scene(root.load(), 800, 600);
        stage.initStyle(StageStyle.UNDECORATED);

        stage.setTitle("App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}