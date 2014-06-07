package com.istudy.helper;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import com.istudy.bean.GameImageBean;
import com.istudy.bean.OptionBean;
import com.istudy.dataset.DataSet;

import android.content.Context;

public class Utils {
	
	public static List<List<GameImageBean>> masterList;
	public static Context applicationContext;
	
	static Random random = new Random(System.nanoTime());
	static LinkedList<Integer> albums; 
	
	public static List<List<GameImageBean>> createMasterList(){
		List<List<GameImageBean>> masterList = new ArrayList<List<GameImageBean>>();		
		for(int i=0;i<DataSet.themes.length;i++)
			masterList.add(i,getThemeList(DataSet.themes[i]));
		
		return masterList;
	}
	
	public static void createAlbumRandomSet(){
		albums = new LinkedList<Integer>();
		for(int i=0;i<DataSet.themeIdArray.length;i++)
			albums.add(i);
		Collections.shuffle(albums, random);
		
	}
	
	public static void createAlbumRandomSet(LinkedList<Integer> list)
	{
		albums = list;
		Collections.shuffle(albums, random);
	}
	public static int getNextAlbum(){
		if(albums.size()>0)
			return albums.removeFirst();
		else
			return 0;
	}
		
	public static int indexForAlbum(String str){
		int index = -1;
		for(int i=0;i<DataSet.themeIdArray.length;i++){
			if(DataSet.themeIdArray[i].compareTo(str) == 0){
				index = i;
				break;
			}
		}
		return index;
	}
	
	private static List<GameImageBean> getThemeList(String[] imgGrpNames){
		
		List<GameImageBean> list = new ArrayList<GameImageBean>();
		
		for(int i=0;i<imgGrpNames.length;i++) 
			list.add(getImageObject(imgGrpNames[i], getOptionsArray(imgGrpNames[i]+"_correct",imgGrpNames[i]+"_alt_")));

		
		Collections.shuffle(list, random);
		return list;
	}
	
	
	private static List<String> getOptionsArray(String opt, String optionName){
		List<String> list = new ArrayList<String>();
		list.add(0,opt);
		
		for(int i=1;i<6;i++)
			list.add(i,optionName+i);
		
		
		Collections.shuffle(list, random);
		
		return list;
	}
	
	private static GameImageBean getImageObject(String title, List<String> optionsList){
		
		Stack<Integer> images = new Stack<Integer>();
		for(int i=0;i<10;i++)
			images.push(getResId(title+"_"+i, "drawable"));
		
		List<OptionBean> options = new ArrayList<OptionBean>();
		for(int i=0;i<optionsList.size();i++)
			options.add(new OptionBean(optionsList.get(i),getResId(optionsList.get(i), "drawable")));
		
		String correctOption = title+"_correct";
		
		return new GameImageBean(images, options, correctOption);
		
	}
	
	public static int getResId(String variableName, String defType) {
		Context context = applicationContext;
	    int identifier = context.getResources().getIdentifier(variableName, defType, context.getPackageName());
	    //Log.d("Utils",variableName+" : "+identifier);
	    return identifier;
	}

}
