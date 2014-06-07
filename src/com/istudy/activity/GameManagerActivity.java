package com.istudy.activity;


import com.example.istudy.R;
import com.istudy.bean.GameImageBean;
import com.istudy.fragment.ImageFragment;
import com.istudy.fragment.ProgressBarFragment;
import com.istudy.helper.ActivityHelper;
import com.istudy.helper.Utils;

import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import android.view.View;

public class GameManagerActivity extends FragmentActivity implements ImageFragment.OnItemClickListener{

	private static final int RESULT_INCOMPLETE = 3;
	
	private int i_time_left;
	private int i_total_score;
	private int i_score;
	private int i_score_diff = 1;
	private int location = 0;
	private int max;
	private static int counter = 0;
	private int step;	
		
	

	
	//private TimerTask mTimerTask;
	final Handler handler = new Handler();
	final Handler delayHandler = new Handler();
	
	private Runnable runnable; 
	//Timer t = new Timer();
	ProgressBarFragment pfag;
	ImageFragment ifag;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityHelper.initialize(GameManagerActivity.this);
		setContentView(R.layout.activity_game_manager);
		
		Intent intent = getIntent();
		location = intent.getIntExtra("location", -1);
		step = intent.getIntExtra("step", 0);
		i_total_score = intent.getIntExtra("total_score", 0);
		max = Utils.masterList.get(location).size();				
		
		if(step>=max){
			Log.d("GameManagerActivity", "step: "+step+" max: "+max);
			result_ok();
		}else{
		
		
			// Check that the activity is using the layout version with
	        // the fragment_container FrameLayout
			if (findViewById(R.id.fragment1) != null) {
				
	            // However, if we're being restored from a previous state,
	            // then we don't need to do anything and should return or else
	            // we could end up with overlapping fragments.
	            if (savedInstanceState != null) {
	                return;
	            }
	            GameImageBean bean = new GameImageBean(Utils.masterList.get(location).get(step));
	            // Create a new Fragment to be placed in the activity layout
	            pfag = new ProgressBarFragment();
	            ifag = ImageFragment.newInstance(bean);
	    		 
	            // Add the fragment to the 'fragment_container' FrameLayout
	            getSupportFragmentManager().beginTransaction()
	                    .add(R.id.fragment1, pfag).commit();
	            
	            getSupportFragmentManager().beginTransaction()
	            .add(R.id.fragment2, ifag).commit();
	        }
		}
				
	}

	
	
	@Override
	protected void onResume(){
		super.onResume();
		gameManager(counter,false);
	}
	
	public void initialize(){
		//t = new Timer();
		i_time_left = 10;
		i_score = 10;
		//counter++;
	}
	
	public void gameManager(int step, boolean flag){
		//if(counter < max){
			initialize();
			if(flag)
				fragmentControl(step);
			startGame();
		//}else{											
		//	result_ok();
		//}
	}
	
	public void fragmentControl(int step){

		ifag = ImageFragment.newInstance(Utils.masterList.get(location).get(step));
		getSupportFragmentManager().beginTransaction()
        .replace(R.id.fragment2, ifag).commit();
		
		pfag = new ProgressBarFragment();
		getSupportFragmentManager().beginTransaction()
        .add(R.id.fragment1, pfag).commit();
	}
	
	public void updateView(){
		i_time_left -= 1;
		if(i_time_left <= 0){
			Log.d("GameManagerActivity ","Time Left : "+i_time_left+" Runnable: "+runnable);
			handler.removeCallbacks(runnable);
			pfag.updateTimeLeft();					
			timeout_false();
		}else /*if(i_time_left%2 == 0)*/{
			i_score -= i_score_diff;
			Log.d("ImageFragment","Time Left: "+i_time_left);
			pfag.updateTimeLeft();
			ifag.updateView();			
		}
	}
	
	public void startGame(){
		
		runnable = new Runnable() {
			
			@Override
			public void run() {
				updateView();
				handler.postDelayed(this, 1000);
			}
		};
				
		runnable.run();						
		
	}
	

	@Override
	public void onOptionItemClicked(final View v, final String id, final String correctOption, final View correctView) {
		handler.removeCallbacks(runnable);
		v.setBackgroundResource(R.drawable.option_selected);
		//Toast.makeText(GameManagerActivity.this, "Id: "+id, Toast.LENGTH_SHORT).show();
		delayHandler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				if(id.compareTo(correctOption) == 0){
					i_total_score += i_score;															
				}else{
					v.setBackgroundResource(R.drawable.option_wrong);										
				}
				timeout_false();
			}
		}, 1000);					
	}
	
	private void timeout_false(){
		ifag.getCorrectView().setBackgroundResource(R.drawable.option_correct);
		delayHandler.postDelayed(new Runnable() {
			
			@Override
			public void run() {			
				//gameManager(counter,true);
				Intent data = new Intent();
				data.putExtra("step", ++step);
				data.putExtra("total_score", i_total_score);
				data.putExtra("location", location);
				setResult(RESULT_INCOMPLETE, data);
				finish();
			}
		}, 1500);
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		handler.removeCallbacks(runnable);
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();		
	}
	
	private void result_ok() {
		Intent intent = new Intent();
		intent.putExtra("just_location", location);
		intent.putExtra("total_score", i_total_score);
		setResult(RESULT_OK,intent);
		finish();
	}

}
