package com.istudy.activity;

import com.example.istudy.R;
import com.istudy.bean.Albums;
import com.istudy.dao.GamePlayDataSource;
import com.istudy.helper.ActivityHelper;
import com.istudy.helper.Utils;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class ScoreActivity extends Activity {

	private static final int RESULT_COMPLETED = 2;
	private int total_score = 0;
	private int just_played = -1;
	private ImageView replay;
	private ImageView home;
	private ImageView versus;
	private ImageView urscore[] = new ImageView[6];
	private ImageView hscore[] = new ImageView[6];
	private GamePlayDataSource datasource;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityHelper.initialize(ScoreActivity.this);
		setContentView(R.layout.activity_transition_score);		
		Intent intent = getIntent();			
		
		datasource = new GamePlayDataSource(this);
	    datasource.open();
		
		
		just_played = intent.getIntExtra("just_location", -1);
		total_score = intent.getIntExtra("total_score", 0);
		
		Log.d("ScoreActivity","score: "+total_score);
		Log.d("ScoreActivity","location: "+just_played);
		
		replay = (ImageView) findViewById(R.id.replay);
		home = (ImageView) findViewById(R.id.home);
		versus = (ImageView) findViewById(R.id.vs);
		
		for(int i=0;i<6;i++){
			urscore[i] = (ImageView) findViewById(Utils.getResId("us_"+i, "id"));
			hscore[i] = (ImageView) findViewById(Utils.getResId("hs_"+i, "id"));
		}
		updatescore();
		
		replay.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setResult(RESULT_COMPLETED);
				finish();
			}
		});
		
		home.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setResult(RESULT_OK);
				finish();
			}
		});
		
		versus.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(ScoreActivity.this, "Challenge a friend", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	@Override
	protected void onPause(){
		datasource.close();
		super.onPause();
		setResult(RESULT_CANCELED);
		finish();
	}
	
	 @Override
	  protected void onResume() {
	    datasource.open();
	    super.onResume();
	  }

	
	private void updatescore(){
		int us[] = prepare(total_score);
		int hs[] = prepare(getHighScore());
		for(int i=0;i<6;i++){
			urscore[i].setImageResource(Utils.getResId("big"+us[i], "drawable"));
			hscore[i].setImageResource(Utils.getResId("small"+hs[i], "drawable"));
		}
	}
	
	private int getHighScore(){
		Albums al = datasource.getGamePlayRecord(just_played);
		int hs = al.getHighscore();
		if(hs>total_score)
			return hs;
		else{
			al.setHighscore(total_score);
			al.setPlayed(1);
			datasource.updateGamePlayRecord(al);
			return total_score;
		}
			
	}
	private int[] prepare(int score){
		int arr[] = {0,0,0,0,0,0};
		String str = Integer.toString(score);
		for(int i=str.length()-1,j=0;i>=0&&j<6;i--,j++){
			arr[j] = str.charAt(i) - '0';
		}
		
		return arr;		
	}

}
