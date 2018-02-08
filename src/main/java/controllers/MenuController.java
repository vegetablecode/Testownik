package controllers;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class MenuController {
	
	private MainController mainController;

	@FXML
	public void beginTest() throws IOException {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/QuestionScreen.fxml"));
		Pane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		QuestionController questionController = loader.getController();
		questionController.setFolderName("baza"); // temporary
		questionController.setQuestions();
		questionController.setMainController(mainController);
		mainController.setScreen(pane);
	}
	
	@FXML
	public void openSettings() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/SettingsScreen.fxml"));
		Pane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		SettingsController settingsController = loader.getController();
		settingsController.setMainController(mainController);
		mainController.setScreen(pane);
	}
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	
	@FXML
	public void exitApp() {
		Platform.exit();
	}
	
}
