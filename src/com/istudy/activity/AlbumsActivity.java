package com.istudy.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.istudy.R;
import com.istudy.adapter.AlbumsAdapter;
import com.istudy.bean.ThemeRowBean;
import com.istudy.dataset.DataSet;
import com.istudy.helper.ActivityHelper;
import com.istudy.helper.Utils;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

public class AlbumsActivity extends Activity {
	private static final int RESULT_COMPLETED = 2;
	private GridView themegrid;
	private List<ThemeRowBean> list = new ArrayList<ThemeRowBean>();
	private AlbumsAdapter adapter;
	private ImageView back_button;
	private int location = -1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityHelper.initialize(AlbumsActivity.this);
		setContentView(R.layout.activity_albums);
		
		location = getIntent().getIntExtra("location", -1);
		list = createList();
		
		
		Log.d("AlbumsActivity","location: "+location);
		
		back_button = (ImageView) findViewById(R.id.icon_back);
		themegrid = (GridView) findViewById(R.id.grid_layout);		
		adapter = new AlbumsAdapter(AlbumsActivity.this, list);
		themegrid.setAdapter(adapter);
								
		themegrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,long id) {			
				view.setBackgroundResource(R.drawable.theme_selected_rounded_bg);				
				if(location == position){
					Intent data = new Intent();
					data.putExtra("location", location);
					setResult(RESULT_COMPLETED, data);
					finish();
				}else{
					location = position;								
					adapter.clear();
					list = createList();
					adapter.addAll(list);
					adapter.notifyDataSetChanged();
				}
				
			}
		});
		
		back_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent data = new Intent();
				data.putExtra("location", location);
				setResult(RESULT_OK, data);
				finish();
			}
		});
	}
		
	
	private List<ThemeRowBean> createList(){
		List<ThemeRowBean> tmplist = new ArrayList<ThemeRowBean>();
		for(int i=0;i<DataSet.themeIdArray.length;i++){
			Integer imgId = Utils.getResId(DataSet.themeIdArray[i]+"_represent", "drawable");
			ThemeRowBean t;
			if(location != i){
				Log.d("AlbumsActivity", "unselected: "+location+" - "+i);
				t = new ThemeRowBean(DataSet.themeTitleArray[i], "theme_rounded_bg", imgId);
			}else{
				t = new ThemeRowBean(DataSet.themeTitleArray[i], "theme_selected_rounded_bg", imgId);
				Log.d("AlbumsActivity", "selected");
			}
			tmplist.add(i,t);
		}
		
		return tmplist;
	}
		

}
