package com.istudy.bean;

public class ThemeRowBean {
	private String title;
	private String bgColor;
	private Integer imgId;
	
	public ThemeRowBean(String title, String bgColor, Integer imgId){
		this.title = title;
		this.setBgColor(bgColor);
		this.imgId = imgId;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Integer getImgId() {
		return imgId;
	}
	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}
		
}
