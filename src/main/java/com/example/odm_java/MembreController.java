package com.example.odm_java;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
		return readMembersFromFile("members.json");
	}

	public static List<Member> readMembersFromFile(String filename) {
		String filePath = new File("src/main/resources/com/example/odm_java/mock_data/" + filename).getAbsolutePath();
		List<Member> members = new ArrayList<>();

		try (FileReader reader = new FileReader(filePath)) {
			JSONTokener tokener = new JSONTokener(reader);
			JSONArray jsonArray = new JSONArray(tokener);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				String ID = jsonObject.getString("ID");
				String ROLE = jsonObject.getString("ROLE");
				String EMAIL = jsonObject.getString("EMAIL");
				String FIRST_NAME = jsonObject.getString("FIRST_NAME");
				String LAST_NAME = jsonObject.getString("LAST_NAME");

				Member member = new Member(ID, ROLE, EMAIL, FIRST_NAME, LAST_NAME);
				members.add(member);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return members;
	}
}
