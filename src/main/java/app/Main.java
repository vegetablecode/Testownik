package app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Question;
import utils.QuestionReader;

public class Main extends Application{

	public static void main(String[] args) throws IOException {
		launch(args);
		QuestionReader reader = new QuestionReader("baza");
		Question testQuestion = reader.getQuestion(0);
		System.out.println(testQuestion.getQuestionFileName());
		System.out.println(testQuestion.getQuestionText());
		System.out.println(testQuestion.getAnswers()[0]);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/MainScreen.fxml"));
		StackPane stackPane = loader.load();
		Scene scene = new Scene(stackPane, 800, 600);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Testownik");
		primaryStage.show();
	}

}
