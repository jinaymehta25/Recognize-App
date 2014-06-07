package com.istudy.fragment;

import com.example.istudy.R;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ProgressBarFragment extends Fragment {
	private View[] progressTicks = new View[10];
	private int timeleft;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
      // Defines the xml file for the fragment
      View view = inflater.inflate(R.layout.progressbar_fragment, container, false);
      timeleft = 10;
      progressTicks[0] = view.findViewById(R.id.ptick0);
      progressTicks[1] = view.findViewById(R.id.ptick1);
      progressTicks[2] = view.findViewById(R.id.ptick2);
      progressTicks[3] = view.findViewById(R.id.ptick3);
      progressTicks[4] = view.findViewById(R.id.ptick4);
      progressTicks[5] = view.findViewById(R.id.ptick5);
      progressTicks[6] = view.findViewById(R.id.ptick6);
      progressTicks[7] = view.findViewById(R.id.ptick7);
      progressTicks[8] = view.findViewById(R.id.ptick8);
      progressTicks[9] = view.findViewById(R.id.ptick9);
      
      return view;
    }
	
	public void updateTimeLeft(){
		timeleft--;
		if(timeleft>=0){
			progressTicks[timeleft].setBackgroundColor(Color.parseColor("#545D69"));
		}
		
	}

}
