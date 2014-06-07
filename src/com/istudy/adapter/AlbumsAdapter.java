package com.istudy.adapter;

import java.util.List;

import com.example.istudy.R;
import com.istudy.bean.ThemeRowBean;
import com.istudy.helper.Utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AlbumsAdapter extends ArrayAdapter<ThemeRowBean> {

	private Context context;
	private List<ThemeRowBean> list;
	
	

	public AlbumsAdapter(Context context, List<ThemeRowBean> list) {
		super(context, R.layout.theme_list_row, list);
		this.context = context;
		this.list = list;
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if(convertView != null)
			view = convertView;
		else
			view = inflater.inflate(R.layout.theme_list_row, parent, false);
		ImageView imageView = (ImageView) ((ViewGroup)view).getChildAt(0);
		TextView title = (TextView) ((ViewGroup)view).getChildAt(1);		
		imageView.setImageResource(list.get(position).getImgId());
		title.setText(list.get(position).getTitle());
		Log.d("ThemeAdapter", "Position: "+position+" Theme: "+list.get(position).getBgColor());
		view.setBackgroundResource(Utils.getResId(list.get(position).getBgColor(), "drawable"));
		//createView(list.get(position));
		return view;
	}
	
	/*private View createView(ThemeRowBean bean){
		LinearLayout layout = new LinearLayout(context);
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		//params.setMargins(0, 0, 0, 10);
		layout.setLayoutParams(params);
		layout.setOrientation(LinearLayout.VERTICAL);
		//layout.setBackgroundResource(R.drawable.theme_rounded_bg);
		layout.setBackgroundResource(Utils.getResId(bean.getBgColor(), "drawable"));
		
		ImageView imageView = new ImageView(context);
		imageView.setLayoutParams(new LayoutParams(100, 150));
		imageView.setImageResource(bean.getImgId());		
		layout.addView(imageView);
		
		TextView titleView = new TextView(context);
		titleView.setPadding(0, 0, 0, 15);
		titleView.setText(bean.getTitle());
		layout.addView(titleView);
		
		return layout;
	}*/
}
