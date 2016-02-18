package com.dpworld.androidapp.adapters;

import java.util.ArrayList;
import com.dpworld.androidapp.R;
import com.dpworld.androidapp.models.ModelTip;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterTipList extends BaseAdapter {
	
	Context c;
	ArrayList<ModelTip> arrayList;
	LayoutInflater inflater;
	
	public AdapterTipList(Context c, ArrayList<ModelTip> arrayList){
		this.c = c;
		this.arrayList = arrayList;
	}

	@Override
	public int getCount() {
		return arrayList.size();
	}

	@Override
	public Object getItem(int position) {
		return arrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder = null;
		View v = convertView;
		
		if(convertView == null){
			LayoutInflater li = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = li.inflate(R.layout.layout_simple_kv, null);
			holder = new Holder();
			holder.containerNumber = (TextView) v.findViewById(R.id.textViewKey);
			holder.truckNumber = (TextView) v.findViewById(R.id.textViewValue);
			v.setTag(holder);
		}else{
			holder = (Holder) v.getTag();
		}
		
		ModelTip item = (ModelTip) getItem(position);
		
		holder.containerNumber.setText(item.getCTR_NBR());
		holder.truckNumber.setText(item.getUPDATED_TRUCK_NO());
		
		return v;
	}
	
	private class Holder{
		TextView containerNumber, truckNumber;
	}

}
