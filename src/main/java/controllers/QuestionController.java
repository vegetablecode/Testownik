package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import model.QuestionList;
import utils.QuestionReader;

public class QuestionController {
	
	private MainController mainController;
	private String folderName;
	private QuestionList questionList;
	
	@FXML
	public void initialize() throws IOException {
		QuestionReader questionReader = new QuestionReader("baza");
		questionList = new QuestionList(questionReader.getNumbOfQuestions());
		for(int i=0; i<questionList.getNumbOfQuestions(); i++) {
			questionList.setQuestion(i, questionReader.getQuestion(i));
		}
		
		// set buttons etc.
		
		System.out.println("QC TEST:");
		System.out.println(questionList.getQuestion(0).getQuestionText());
		System.out.println(questionList.getQuestion(5).getQuestionText());
	}
	
	@FXML
	public void backToMenu() {
		mainController.loadMenuScreen();
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	

}
