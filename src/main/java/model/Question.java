package model;

public class Question {
	
	private String questionFileName;
	private boolean[] correctAnswers;
	private String questionText;
	private String[] answers;
	private int numbOfRep;
	
	public Question(String questionFileName, boolean[] correctAnswers, String questionText, String[] answers) {
		this.questionFileName = questionFileName;
		this.correctAnswers = correctAnswers;
		this.questionText = questionText;
		this.answers = answers;
		numbOfRep = 1; // default
	}
	
	public int getNumbOfAnswers() {
		return answers.length;
	}
	
	public String getQuestionFileName() {
		return questionFileName;
	}
	
	public boolean[] getCorrectAnswers() {
		return correctAnswers;
	}
	
	public String getQuestionText() {
		return questionText;
	}
	
	public String[] getAnswers() {
		return answers;
	}
	
	public int getNumbOfRep() {
		return numbOfRep;
	}
	
	public boolean checkIfCorrect(boolean[] userAnswers) {
		for(int i=0; i<userAnswers.length; i++)
			if(userAnswers[i]!=correctAnswers[i])
				return false;
			return true;
	}
	
	public void incNumbOfRep(int n) {
		numbOfRep+=n;
	}
	
	public void decNumbOfRep(int n) {
		numbOfRep-=n;
	}
	
}
