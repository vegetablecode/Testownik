package model;

public class Question {
	
	private boolean[] correctAnswers;
	private String questionText;
	private String[] answers;
	private int numbOfRep;
	
	public Question(boolean[] correctAnswers, String questionText, String[] answers, int numbOfRep) {
		this.correctAnswers = correctAnswers;
		this.questionText = questionText;
		this.answers = answers;
		this.numbOfRep = numbOfRep;
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
	
	public boolean checkIfCorrect(boolean userAnswers) {
		if(correctAnswers.equals(userAnswers))
			return true;
		else return false;
	}
	
	public void incNumbOfRep(int n) {
		numbOfRep+=n;
	}
	
	public void decNumbOfRep(int n) {
		numbOfRep-=n;
	}
	
}
