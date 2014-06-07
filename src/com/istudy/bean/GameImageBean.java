package com.istudy.bean;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class GameImageBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Stack<Integer> images;	
	private List<OptionBean> options;
	private String correctOption;
	
		
	public GameImageBean(Stack<Integer> images,List<OptionBean> options,String correctOption){
		this.images = images;
		this.options = options;
		this.setCorrectOption(correctOption);
		Collections.shuffle(this.options, new Random(System.nanoTime()));
				
	}
	
	@SuppressWarnings("unchecked")
	public GameImageBean(GameImageBean bean){
		this.images = (Stack<Integer>) bean.images.clone();
		this.options = bean.options;
		this.correctOption = bean.correctOption;
		Collections.shuffle(this.options, new Random(System.nanoTime()));
	}

	public List<OptionBean> getOptions() {
		return options;
	}

	public void setOptions(List<OptionBean> options) {
		this.options = options;
	}

	public Stack<Integer> getImages() {
		return images;
	}
	public void setImages(Stack<Integer> images) {
		this.images = images;
	}

	public String getCorrectOption() {
		return correctOption;
	}

	public void setCorrectOption(String correctOption) {
		this.correctOption = correctOption;
	}
	
	
	
	
}
