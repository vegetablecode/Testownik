package controllers;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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
	@FXML
	private Label progressLabel;
	@FXML
	private ProgressBar progressBar;
	@FXML
	private Button nextButton;
	@FXML
	private Button checkButton;
	@FXML
	private Button finishButton;
	
	// ----- FXML METHODS ----- //
	@FXML
	public void backToMenu() {
		mainController.loadMenuScreen();
	}
	@FXML
	public void checkAnswers() {
		// check the answer
		Question currentQuestion = questionList.getQuestion(questionNumber);
		if(currentQuestion.checkIfCorrect(getUserAnswers(currentQuestion))) {
			questionList.getQuestion(questionNumber).decNumbOfRep(1);
			questionList.incNumbOfCorrectAnswers();
		}
		else {
			questionList.getQuestion(questionNumber).incNumbOfRep(1); // inc of rep should be choosed before the test
			questionList.incNumbOfWrongAnswers();
		}
		// display the score
		displayRightAnswers(currentQuestion);
		displayStats();
		// set buttons visible
		nextButton.setVisible(true);
		checkButton.setVisible(false);
	}
	@FXML
	public void nextQuestion() {
		System.out.println("QN"+questionNumber); // DEBUG
		// check if questionNumber < number of questions
		if(questionNumber<questionList.getNumbOfQuestions()-1) {
			System.out.println("QN: plus1"); // DEBUG
			questionNumber++; 
		} else {
			System.out.println("zerowanie"); // DEBUG
			questionNumber = 0;
		}
		// skip learned question
		int counter = 0;
		while(questionList.getQuestion(questionNumber).getNumbOfRep()==0) {
			System.out.println("skipQuestion"); // DEBUG
			questionNumber++;
			if(questionNumber==questionList.getNumbOfQuestions()) {
				if(counter<2) {
					questionNumber=0;
					counter++;
				} else {
					nextButton.setVisible(false);
					checkButton.setVisible(false);
					finishButton.setVisible(true);
					questionNumber = -1;
					break;	
				}
			}
		}
		if(questionNumber!=-1)
			displayQuestion(questionList.getQuestion(questionNumber));
		else {
			System.out.println("Koniec testu"); // DEBUG
		}
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
		finishButton.setVisible(false);
		questionTextLabel.setWrapText(true);
		for(int i=0; i<labelList.size(); i++) {
			labelList.get(i).setWrapText(true);
		}
	}
	
	@FXML
	public void finishTest() {
		displayEndScreen();
		// display finish screen
		// display full stats, time, etc
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
		if(question.getQuestionText().charAt(0)=='[') {
			questionTextLabel.setText(question.getQuestionText());
			questionReader.getImage(question.getQuestionText());
			//questionTextImage.setImage(questionReader.getImage(question.getQuestionText()));
		} else {
			questionTextLabel.setText(question.getQuestionText());
		}
		
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
		displayStats();
		nextButton.setVisible(false);
		checkButton.setVisible(true);
	}
	
	public void displayStats() {
		// temp
		double numbOfLearnedQuestions = questionList.getNumbOfLearnedQuestions();
		double numbOfQuestions = questionList.getNumbOfQuestions();
		// progress label & bar
		String progressValue = "";
		double progressPercentage = numbOfLearnedQuestions/numbOfQuestions;
		progressValue += String.valueOf((int)numbOfLearnedQuestions);
		progressValue += " / ";
		progressValue += String.valueOf((int)numbOfQuestions);
		progressLabel.setText(progressValue);
		progressBar.setProgress(progressPercentage);
		
	}
	
	public void displayRightAnswers(Question question) {
		for(int i=0; i<question.getNumbOfAnswers(); i++) {
			if(question.getCorrectAnswers()[i]==true)
				labelList.get(i).setTextFill(Color.RED);
		}
	}
	
	public void displayEndScreen() {
		System.out.println("CALL FINISH SCREEN");
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
