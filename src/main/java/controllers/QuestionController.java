package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.QuestionList;
import utils.QuestionReader;

public class QuestionController {
	
	private MainController mainController;
	private String folderName;
	private QuestionList questionList;
	private QuestionReader questionReader;
	
	@FXML
	private Label questionTextLabel;
	@FXML
	private Button answerOneButton;
	
	
	@FXML
	public void backToMenu() {
		mainController.loadMenuScreen();
	}

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
		
		//questionTextLabel.setText("SIEMA");
		//questionTextLabel.setText(questionList.getQuestion(0).getQuestionText());
		//answerOneButton.setText(questionList.getQuestion(0).getAnswers()[0]);
		System.out.println(questionList.getQuestion(0).getQuestionText());
		System.out.println(questionList.getQuestion(5).getQuestionText());
	}
	

}
