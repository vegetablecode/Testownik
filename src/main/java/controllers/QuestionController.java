package controllers;

import javafx.fxml.FXML;

public class QuestionController {
	
	private MainController mainController;
	
	@FXML
	public void backToMenu() {
		mainController.loadMenuScreen();
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

}
