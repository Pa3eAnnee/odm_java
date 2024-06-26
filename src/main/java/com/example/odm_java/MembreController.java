package com.example.odm_java;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MembreController extends Controller implements Initializable {
	@FXML
	private TableView<Member> tableView;
	@FXML private TableColumn<Member, String> colId;
	@FXML private TableColumn<Member, String> colRole;
	@FXML private TableColumn<Member, String> colEmail;
	@FXML private TableColumn<Member, String> colFirstname;
	@FXML private TableColumn<Member, String> colLastname;

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
		return Utils.readMembersFromFile("members.json");
	}
}
