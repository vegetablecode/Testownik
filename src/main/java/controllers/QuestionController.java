package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.Question;
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
	private Label aLabel;
	@FXML
	private Label bLabel;
	@FXML
	private Label cLabel;
	@FXML
	private Label dLabel;
	@FXML
	private Label eLabel;
	@FXML
	private Label fLabel;
	@FXML
	private Label gLabel;
	@FXML
	private Label hLabel;
	@FXML
	private Label iLabel;
	@FXML
	private Label jLabel;
	@FXML
	private ImageView aImage;
	@FXML
	private ImageView bImage;
	@FXML
	private ImageView cImage;
	@FXML
	private ImageView dImage;
	@FXML
	private ImageView eImage;
	@FXML
	private ImageView fImage;
	@FXML
	private ImageView gImage;
	@FXML
	private ImageView hImage;
	@FXML
	private ImageView iImage;
	@FXML
	private ImageView jImage;
	@FXML
	private CheckBox aCheckBox;
	@FXML
	private CheckBox bCheckBox;
	@FXML
	private CheckBox cCheckBox;
	@FXML
	private CheckBox dCheckBox;
	@FXML
	private CheckBox eCheckBox;
	@FXML
	private CheckBox fCheckBox;
	@FXML
	private CheckBox gCheckBox;
	@FXML
	private CheckBox hCheckBox;
	@FXML
	private CheckBox iCheckBox;
	@FXML
	private CheckBox jCheckBox;
	
	// FXML arrays
	//private Label[] labels = {aLabel, bLabel, cLabel, dLabel, eLabel, fLabel, gLabel, hLabel, iLabel, jLabel};
	//private ImageView[] images = {aImage, bImage, cImage, dImage, eImage, fImage, gImage, hImage, iImage, jImage};
	//private CheckBox[] checks = {aCheckBox, bCheckBox, cCheckBox, dCheckBox, eCheckBox, fCheckBox, gCheckBox, hCheckBox, iCheckBox, jCheckBox};
	
	// FXML methods
	@FXML
	public void backToMenu() {
		mainController.loadMenuScreen();
	}
	@FXML
	public void checkAnswers() {
		System.out.println("CHECKING THE ANSWERS");
	}
	
	
	
	// class setup
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
		
		displayQuestion(questionList.getQuestion(0));
		
		//System.out.println(questionList.getQuestion(0).getQuestionText());
		//System.out.println(questionList.getQuestion(5).getQuestionText());
	}
	
	// view setup
	public void displayQuestion(Question question) {
		questionTextLabel.setText(question.getQuestionText());
		
		bLabel.setWrapText(true);
		
		aLabel.setText(question.getAnswers()[0]);
		bLabel.setText(question.getAnswers()[1]);
		cLabel.setText(question.getAnswers()[2]);
		dLabel.setText(question.getAnswers()[3]);
		
	}
}
