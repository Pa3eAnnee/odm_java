package com.example.odm_java;

import com.example.odm_java.model.DemoModel;
import com.example.odm_java.view.RootPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the main class to start the application.
 *
 * @author Sacha Schmid
 * @author Rinesch Murugathas
 */
public class Demo extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        DemoModel model = new DemoModel();
        RootPane rootPanel = new RootPane(model);

        Scene scene = new Scene(rootPanel);

        primaryStage.titleProperty().bind(model.getFormInstance().titleProperty());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
