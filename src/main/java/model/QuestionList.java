package model;

public class QuestionList {
	
	private int numbOfQuestions;
	private Question[] questions;
	
	// stats (maybe i should create another class with stats...)
	private int numbOfCorrectAnswers;
	private int numbOfWrongAnswers;
	
	public QuestionList(int numbOfQuestions) {
		this.numbOfQuestions = numbOfQuestions;
		questions = new Question[numbOfQuestions];
		numbOfCorrectAnswers = 0;
		numbOfWrongAnswers = 0;
	}
	
	public void setQuestion(int index, Question question) {
		questions[index] = question;
	}

	public void incNumbOfCorrectAnswers() {
		numbOfCorrectAnswers++;
	}
	
	public void incNumbOfWrongAnswers() {
		numbOfWrongAnswers++;
	}
	
	public Question getQuestion(int index) {
		return questions[index];
	}
	
	public int getNumbOfQuestions() {
		return numbOfQuestions;
	}
	
	public int getNumbOfCorrectAnswers() {
		return numbOfCorrectAnswers;
	}
	
	public int getNumbOfWrongAnswers() {
		return numbOfWrongAnswers;
	}

}
