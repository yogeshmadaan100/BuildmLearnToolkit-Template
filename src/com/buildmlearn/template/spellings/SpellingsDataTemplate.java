package com.buildmlearn.template.spellings;

public class SpellingsDataTemplate {
	private String mWord;
	private String meaning;
	public SpellingsDataTemplate(String word,String meaning) {
		// TODO Auto-generated constructor stub
		this.mWord=word;
		this.meaning=meaning;
	}
	public String getmWord() {
		return mWord;
	}
	public void setmWord(String mWord) {
		this.mWord = mWord;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	
}
