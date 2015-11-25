package com.dpworld.androidapp.adapters;

import java.util.ArrayList;
import java.util.List;

import com.dpworld.androidapp.R;
import com.dpworld.androidapp.models.ModelContainerInquiry;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterContainerInquiry extends BaseAdapter {
	
	Context c;
	List<ModelContainerInquiry> arrayList;
	LayoutInflater inflater;
	
	public AdapterContainerInquiry(Context c, List<ModelContainerInquiry> arrayList){
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

        if (convertView == null) {
        	LayoutInflater li = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        	v = li.inflate(R.layout.layout_simple_kv, null);
        	holder = new Holder();
            holder.fieldName = (TextView) v.findViewById(R.id.textViewKey);
            holder.fieldValue = (TextView) v.findViewById(R.id.textViewValue);
            v.setTag(holder);
        }
        else {
            holder = (Holder) v.getTag();
        }
         
        ModelContainerInquiry rowItem = (ModelContainerInquiry) getItem(position);
         
        holder.fieldName.setText(rowItem.getFIELD_NAME());
        holder.fieldValue.setText(rowItem.getFIELD_VALUE());
         	
		return v;
	}
	
	private class Holder{
		TextView fieldName,fieldValue;
	}

}
