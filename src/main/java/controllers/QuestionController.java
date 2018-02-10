package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.QuestionList;
import utils.QuestionReader;

public class QuestionController {
	
	private MainController mainController;
	private String folderName;
	private QuestionList questionList;
	private QuestionReader questionReader;
	
	// FXML attributes
	@FXML
	private Label questionTextLabel;
	@FXML
	private Label labelA;
	@FXML
	private ImageView imageA;
	@FXML
	private CheckBox checkboxA;
	
	// FXML methods
	@FXML
	public void backToMenu() {
		mainController.loadMenuScreen();
	}
	@FXML
	public void checkAnswers() {
		System.out.println("CHECKING THE ANSWERS");
	}
	@FXML
	public void selectA() {
		if(checkboxA.isSelected()) {
			System.out.println("A SELECTED");
		}
		else
			System.out.println("A UNSELECTED");
	}
	
	
	// class setup
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	
	public void setFolderName(String folderName) {
		this.folderName = folderName;
		System.out.println("DZIALA");
	}

	public void setQuestions() throws IOException {
		questionReader = new QuestionReader(folderName);
		questionList = new QuestionList(questionReader.getNumbOfQuestions());
		for(int i=0; i<questionList.getNumbOfQuestions(); i++) {
			questionList.setQuestion(i, questionReader.getQuestion(i));
		}
		
		// set buttons etc.
		
		System.out.println(questionList.getQuestion(0).getQuestionText());
		System.out.println(questionList.getQuestion(5).getQuestionText());
	}
	
	// view setup

}
