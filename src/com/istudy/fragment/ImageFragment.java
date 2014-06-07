package com.istudy.fragment;

import java.util.List;
import java.util.Stack;

import com.example.istudy.R;
import com.istudy.bean.GameImageBean;
import com.istudy.bean.OptionBean;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageFragment extends Fragment {
	
	
	private ImageView img;
	//private ProgressBar mProgressBar;
	private ImageView[] options = new ImageView[6];
	private String correctOption;
	private ImageView correctView;
	private GameImageBean obj;
	
	private OnItemClickListener listener;
	
	/*// AudioManager
	private AudioManager mAudioManager;
	// SoundPool
	private SoundPool mSoundPool;

	private int mInterSound;
	private int mEndSound;
		
	// Audio volume
	private float mStreamVolume;
*/
	Stack<Integer> imgStack;
	
	
	// Define the events that the fragment will use to communicate
	  public interface OnItemClickListener {
	    public void onOptionItemClicked(View v, String id, String correctOption, View correctView);
	  }
	
	  
	

	public ImageView getCorrectView() {
		return correctView;
	}


	public void setCorrectView(ImageView correctView) {
		this.correctView = correctView;
	}


	public static ImageFragment newInstance(GameImageBean GameObj) {
		ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putSerializable("GameObj", GameObj);
        fragment.setArguments(args);
        return fragment;
    }

	
	@Override
	  public void onAttach(Activity activity) {
	    super.onAttach(activity);
	      if (activity instanceof OnItemClickListener) {
	        listener = (OnItemClickListener) activity;
	      } else {
	        throw new ClassCastException(activity.toString()
	            + " must implement ImageFragment.OnItemClickListener");
	      }
	  }
	
	@Override
	public void onResume() {
		super.onResume();
		
		/*mAudioManager = (AudioManager) getActivity().getSystemService(getActivity().AUDIO_SERVICE);

		mStreamVolume = (float) mAudioManager
				.getStreamVolume(AudioManager.STREAM_MUSIC)
				/ mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

		mSoundPool = new SoundPool(10,AudioManager.STREAM_MUSIC,0);
		
		mInterSound = mSoundPool.load(getActivity(),R.raw.clang_x,1);
		mEndSound = mSoundPool.load(getActivity(),R.raw.buzzer_x,1);
*/		
	}
	
	@Override
	public void onPause() {
		
		// TODO - Release all SoundPool resources

		/*if (null != mSoundPool) {
			mSoundPool.unload(mInterSound);
			mSoundPool.unload(mEndSound);
			mSoundPool.release();
			mSoundPool = null;
		}*/
		
		super.onPause();
	}
	
		
	public void updateView(){
		// set values to starting format
		//mProgressBar.setProgress(new_time);
		//mSoundPool.play(mInterSound, mStreamVolume, mStreamVolume, 1, 0, 1.0f);
		Log.d("ImageFragment", "Update - Image Stack: "+imgStack);
		if(imgStack!=null && imgStack.size()>0){
			Log.d("ImageFragment", "Update - Size of Stack: "+imgStack.size());
			img.setImageResource(imgStack.pop());
		}
		
	}
	
	@Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	      Bundle savedInstanceState) {
	      // Defines the xml file for the fragment
	      View view = inflater.inflate(R.layout.image_fragment, container, false);
	      // Setup handles to view objects here
	      obj = (GameImageBean) getArguments().getSerializable("GameObj");
	      imgStack = obj.getImages();
	      List<OptionBean> opts= obj.getOptions();
	      correctOption = obj.getCorrectOption();
	      //mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
	      img = (ImageView) view.findViewById(R.id.main_image);
	      options[0] = (ImageView) view.findViewById(R.id.thumbs1);
		  options[1] = (ImageView) view.findViewById(R.id.thumbs2);
		  options[2] = (ImageView) view.findViewById(R.id.thumbs3);
		  options[3] = (ImageView) view.findViewById(R.id.thumbs4);
		  options[4] = (ImageView) view.findViewById(R.id.thumbs5);
		  options[5] = (ImageView) view.findViewById(R.id.thumbs6);


			
	      for(int i=0;i<options.length;i++){
	    	  options[i].setTag(opts.get(i).getOptionName());
	    	  options[i].setImageResource(opts.get(i).getOptionId());
	    	  
	    	  if(((String)options[i].getTag()).compareTo(correctOption) == 0)
	    		  correctView = options[i];
	    	  
	      }
				
	      Log.d("ImageFragment", "Create - Size of Stack: "+imgStack.size());
	      img.setImageResource(imgStack.pop());
	      //mProgressBar.setMax(i_time_left);
	      //mProgressBar.setProgress(i_time_left);
	      
	    //OnclickListeners
		for(int i=0;i<options.length;i++)
			options[i].setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						listener.onOptionItemClicked(v,(String) v.getTag(),correctOption,correctView);

					}
				});
		

	      return view;
	    }
}
