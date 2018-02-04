package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import model.Question;

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
		for(int i=0; i<numbOfQuestions; i++)
			fileNames[i] = files[i].getPath();
	}
	
	public int readFiles() throws IOException {
		files = f.listFiles(textFilter);
		return files.length;
	}
	
	public Question getQuestion(int index) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(files[index]), "iso8859_2"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       System.out.println(line);
		    }
		}
		return null;
	}
	
}
