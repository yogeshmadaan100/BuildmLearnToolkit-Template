package com.buildmlearn.template.mlearning;

public class LearningDataTemplate {
	private String mTitle;
	private String description;
	
	public LearningDataTemplate(String title,String desciption) {
		// TODO Auto-generated constructor stub
		this.mTitle=title;
		this.description=desciption;
	}
	public String getmTitle() {
		return mTitle;
	}
	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
