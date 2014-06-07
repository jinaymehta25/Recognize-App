package com.istudy.bean;

public class OptionBean {
	private String optionName;
	private int optionId;
	
	public OptionBean(String optionName, int optionId){
		this.optionName = optionName;
		this.optionId = optionId;
	}
	public int getOptionId() {
		return optionId;
	}
	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

}
