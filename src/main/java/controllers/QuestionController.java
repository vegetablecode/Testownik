package controllers;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import model.Question;
import model.QuestionList;
import utils.QuestionReader;

public class QuestionController {
	
	private MainController mainController;
	private String folderName;
	private QuestionList questionList;
	private QuestionReader questionReader;
	
	private int questionNumber;
	
	@FXML
	private List<Label> labelList;
	@FXML
	private List<CheckBox> checkBoxList;
	@FXML
	private List<ImageView> imageList;
	@FXML
	private Label questionTextLabel;
	@FXML
	private ImageView questionTextImage;
	@FXML
	private Label questionFileLabel;
	
	// ----- FXML METHODS ----- //
	@FXML
	public void backToMenu() {
		mainController.loadMenuScreen();
	}
	@FXML
	public void checkAnswers() {
		Question currentQuestion = questionList.getQuestion(questionNumber);
		if(currentQuestion.checkIfCorrect(getUserAnswers(currentQuestion))) {
			questionList.getQuestion(questionNumber).decNumbOfRep(1);
			questionList.incNumbOfCorrectAnswers();
		}
		else {
			questionList.getQuestion(questionNumber).incNumbOfRep(1); // inc of rep should be choosed before the test
			questionList.incNumbOfWrongAnswers();
		}
		displayRightAnswers(currentQuestion);
		
	}
	@FXML
	public void nextQuestion() {
		questionNumber++;
		displayQuestion(questionList.getQuestion(questionNumber));
	}
	
	// ----- CLASS SETUP ----- //
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public void setQuestions() throws IOException {
		questionReader = new QuestionReader(folderName);
		questionList = new QuestionList(questionReader.getNumbOfQuestions());
		for(int i=0; i<questionList.getNumbOfQuestions(); i++) {
			questionList.setQuestion(i, questionReader.getQuestion(i));
		}
		
		questionNumber = 0;
		displayQuestion(questionList.getQuestion(questionNumber));
		
		// view setup
		for(int i=0; i<labelList.size(); i++) {
			labelList.get(i).setWrapText(true);
		}
	}
	
	// ----- VIEW SETUP ----- //
	public void displayQuestion(Question question) {
		// set all answers visible & unselect checkboxes
		for(int i=0; i<labelList.size(); i++) {
			labelList.get(i).setVisible(true);
			labelList.get(i).setTextFill(Color.BLACK);
			checkBoxList.get(i).setVisible(true);
			checkBoxList.get(i).setSelected(false);
			imageList.get(i).setVisible(true);
		}
		
		// display question and answers
		questionTextLabel.setText(question.getQuestionText());
		
		for(int i=0; i<question.getNumbOfAnswers(); i++) {
			labelList.get(i).setText(question.getAnswers()[i]);
		}
		
		// set unused answers not visible
		for(int i=question.getNumbOfAnswers(); i<labelList.size(); i++) {
			labelList.get(i).setVisible(false);
			checkBoxList.get(i).setVisible(false);
			imageList.get(i).setVisible(false);
		}
		
		// display data
		questionFileLabel.setText(question.getQuestionFileName());
	}
	
	public void displayStats() {
		//
	}
	
	public void displayRightAnswers(Question question) {
		for(int i=0; i<question.getNumbOfAnswers(); i++) {
			if(question.getCorrectAnswers()[i]==true)
				labelList.get(i).setTextFill(Color.RED);
		}
	}
	
	// ----- METHODS ----- //
	public boolean[] getUserAnswers(Question question) {
		boolean[] userAnswers = new boolean[question.getNumbOfAnswers()];
		for(int i=0; i<question.getNumbOfAnswers(); i++) {
			if(checkBoxList.get(i).isSelected()==true)
				userAnswers[i] = true;
			else userAnswers[i] = false;
		}
		return userAnswers;
	}
	
}
