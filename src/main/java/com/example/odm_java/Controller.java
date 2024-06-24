package com.example.odm_java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    public Button planningButton;
    private Stage stage;
    private Scene scene;
    private Parent root;

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

    public void goToPlanning(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Planning.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToMembre(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Membre.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
