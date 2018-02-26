package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import javafx.scene.image.Image;
import model.Question;

// okay, this class needs to be fixed, cuz it's written so bad

public class QuestionReader {

	private String[] fileNames;
	private int numbOfQuestions;

	Scanner in;
	private File f;
	private File[] files;
	FilenameFilter textFilter = new FilenameFilter() {
		public boolean accept(File dir, String name) {
			return name.toLowerCase().endsWith(".txt");
		}
	};

	public QuestionReader(String folderName) throws IOException {
		f = new File(folderName);

		// check number of questions
		numbOfQuestions = readFiles();
		fileNames = new String[numbOfQuestions];
		for (int i = 0; i < numbOfQuestions; i++) {
			fileNames[i] = files[i].getPath();
			//System.out.println(fileNames[i]);
		}
		
	}

	public int readFiles() throws IOException {
		files = f.listFiles(textFilter);
		return files.length;
	}
	
	public int getNumbOfQuestions() {
		return numbOfQuestions;
	}

	public Question getQuestion(int index) throws IOException {
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream(files[index]), "Cp1250"))) {
			String line;
			// first line: number of answers
			line = br.readLine();
			boolean[] correctAnswers = new boolean[line.length() - 1];
			String[] answers = new String[correctAnswers.length];
			for (int i = 1; i < line.length(); i++) {
				if (line.charAt(i) == '0')
					correctAnswers[i - 1] = false;
				else
					correctAnswers[i - 1] = true;
			}
			// the rest of lines: title and answers
			String questionText = br.readLine();
			for (int i = 0; i < correctAnswers.length; i++) {
				answers[i] = br.readLine();
			}
			return new Question(files[index].getPath(), correctAnswers, questionText, answers);
		} catch (IOException c) {
			c.getStackTrace();
			return null;
		}
	}

	public Image getImage(String folderName, String questionText) throws FileNotFoundException {
		String nameOfFile = removeMarkers(questionText);
		System.out.println(nameOfFile);
		String tempName = folderName + "//" + nameOfFile;
		System.out.println(tempName);
		Image outputImage = new Image(new FileInputStream(tempName));
		return outputImage;
	}
	
	public String removeMarkers(String questionText) {
		char[] charText = new char[questionText.length()-11];
		int counter = 0;
		int i = 5;
		while(questionText.charAt(i)!='[') {
			charText[counter] = questionText.charAt(i);
			counter++;
			i++;
		}
		return new String(charText);
	}

}
