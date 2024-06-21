package com.example.odm_java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class Controller {

    @FXML
    private Button membresButton;

    @FXML
    private Button planningButton;

    @FXML
    private Button listeDeCourseButton;

    @FXML
    private Button projetsButton;

    @FXML
    public void handleButtonClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String buttonText = clickedButton.getText();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Button Clicked");
        alert.setHeaderText(null);
        alert.setContentText(buttonText);
        alert.showAndWait();
    }
}
