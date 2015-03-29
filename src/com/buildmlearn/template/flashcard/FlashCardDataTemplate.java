package com.buildmlearn.template.flashcard;

public class FlashCardDataTemplate {
	
	private String question;
	private String answer;
	private String imageUrl;
	private String hint;
	
	public FlashCardDataTemplate(String question,String answer,String imageUrl,String hint) {
		// TODO Auto-generated constructor stub
		this.question=question;
		this.answer=answer;
		this.imageUrl=imageUrl;
		this.hint=hint;
		
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getHint() {
		return hint;
	}
	public void setHint(String hint) {
		this.hint = hint;
	}
	
	
	

}
