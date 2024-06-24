package com.example.odm_java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.InputStream;
import java.util.List;

public class Controller {

    @FXML
    private Button membresButton;

    @FXML
    private Button planningButton;

    @FXML
    private Button listeDeCourseButton;

    @FXML
    private Button projetsButton;

    @FXML private TableView<Member> tableView;
    @FXML private TableColumn<Member, String> colId;
    @FXML private TableColumn<Member, String> colRole;
    @FXML private TableColumn<Member, String> colEmail;
    @FXML private TableColumn<Member, String> colFirstname;
    @FXML private TableColumn<Member, String> colLastname;


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

    @FXML
    private void handleCloseButtonClick() {
        System.exit(0);
    }
}
