package com.dpworld.androidapp.adapters;

import com.dpworld.androidapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuGridAdapter extends BaseAdapter {
	
	private Context context;
	private Integer[] thumbIds;
	private String[] itemNames;
	private static LayoutInflater inflater = null;
	
	public MenuGridAdapter(Context c, Integer[] imgIds, String[] optionsNames){
		context = c;
		thumbIds = imgIds;
		itemNames = optionsNames;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return thumbIds.length;
	}

	@Override
	public Object getItem(int position) {
		return itemNames[position];
	}

	@Override
	public long getItemId(int id) {
		return 0;
	}
	
	public class GridViewHolder{
		ImageView iv; TextView tv;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		GridViewHolder holder = new GridViewHolder();
		View rootView;
		
		rootView = inflater.inflate(R.layout.layout_menu_grid, null);
		holder.iv = (ImageView) rootView.findViewById(R.id.grid_item_image);
		holder.tv = (TextView) rootView.findViewById(R.id.grid_item_name);
		
		holder.iv.setImageResource(thumbIds[position]);
		holder.tv.setText(itemNames[position]);
		
		return rootView;
	}

}
