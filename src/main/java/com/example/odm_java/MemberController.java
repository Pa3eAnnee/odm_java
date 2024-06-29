package com.example.odm_java;

import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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

public class MemberController extends Controller implements Initializable {
	@FXML
	private TableView<Member> tableView;
	@FXML private TableColumn<Member, String> colId;
	@FXML private TableColumn<Member, String> colRole;
	@FXML private TableColumn<Member, String> colEmail;
	@FXML private TableColumn<Member, String> colFirstname;
	@FXML private TableColumn<Member, String> colLastname;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// Call API
		Api.getMembers();

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
//		String filePath = new File("src/main/resources/com/example/odm_java/mock_data/" + filename).getAbsolutePath();
		String filePath = new File("src/main/resources/data/" + filename).getAbsolutePath();
		List<Member> members = new ArrayList<>();

		try (FileReader reader = new FileReader(filePath)) {
			JSONTokener tokener = new JSONTokener(reader);
			JSONArray jsonArray = new JSONArray(tokener);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				Number ID = jsonObject.getNumber("id");
				String ROLE = jsonObject.getString("role");
				String EMAIL = jsonObject.getString("email");
				String FIRST_NAME = jsonObject.getString("first_name");
				String LAST_NAME = jsonObject.getString("last_name");

				Member member = new Member(ID, ROLE, EMAIL, FIRST_NAME, LAST_NAME);
				members.add(member);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return members;
	}

	public void addMember(ActionEvent actionEvent) throws IOException {

	}


	public void updateMember(ActionEvent actionEvent) {
		Member selectedMember = tableView.getSelectionModel().getSelectedItem();
		if (selectedMember != null) {
			System.out.println("Selected member: " + selectedMember.getID() + ", " + selectedMember.getROLE() + ", " + selectedMember.getEMAIL() + ", " + selectedMember.getFIRST_NAME() + ", " + selectedMember.getLAST_NAME());
		} else {
			System.out.println("No member selected.");
		}
	}

	public void deleteMember(ActionEvent actionEvent) {
		Member selectedMember = tableView.getSelectionModel().getSelectedItem();
		if (selectedMember != null) {
			System.out.println("Selected member: " + selectedMember.getID() + ", " + selectedMember.getROLE() + ", " + selectedMember.getEMAIL() + ", " + selectedMember.getFIRST_NAME() + ", " + selectedMember.getLAST_NAME());
		} else {
			System.out.println("No member selected.");
		}
	}
}
