package com.example.odm_java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Controller implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("ROLE"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("EMAIL"));
        colFirstname.setCellValueFactory(new PropertyValueFactory<>("FIRST_NAME"));
        colLastname.setCellValueFactory(new PropertyValueFactory<>("LAST_NAME"));

        tableView.getItems().setAll(loadMembers());
    }

    public static List<Member> loadMembers() {
        String filePath = new File("src/main/resources/com/example/odm_java/mock_data/members.json").getAbsolutePath();
        return Utils.readMembersFromFile(filePath);
    }


}
