package com.example.odm_java;

import javafx.event.ActionEvent;

import java.io.IOException;

public class NavbarController extends Controller {
	public void goToPlanning(ActionEvent event) throws IOException {
		ViewSwitcher.switchTo(View.PLANNING);
	}

	public void goToMembre(ActionEvent event) throws IOException {
		ViewSwitcher.switchTo(View.MEMBRE);
	}

	public void goToMembreAdd(ActionEvent event) throws IOException {
		ViewSwitcher.switchTo(View.ADD_MEMBRE);
	}

}
