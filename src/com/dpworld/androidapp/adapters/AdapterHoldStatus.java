package com.dpworld.androidapp.adapters;

import java.util.List;

import com.dpworld.androidapp.R;
import com.dpworld.androidapp.models.ModelHoldStatus;
import com.dpworld.androidapp.models.ModelVesselSchedule;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterHoldStatus extends BaseAdapter{
	
	Context c;
	List<ModelHoldStatus> arrayList;
	LayoutInflater inflater;
	
	public AdapterHoldStatus(Context c, List<ModelHoldStatus> arrayList){
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
			v = li.inflate(R.layout.layout_hold_status, null);
			holder = new Holder();
			holder.eqnbrVal = (TextView) v.findViewById(R.id.hs_eq_nbr_val);
			holder.heldReason = (TextView) v.findViewById(R.id.hs_held_reason);
			holder.heldReasonVal = (TextView) v.findViewById(R.id.hs_held_reason_val);
			holder.holdDate = (TextView) v.findViewById(R.id.hs_hold_date);
			holder.holdDateVal = (TextView) v.findViewById(R.id.hs_hold_date_val);
			holder.releaseDate = (TextView) v.findViewById(R.id.hs_release_date);
			holder.releaseDateVal = (TextView) v.findViewById(R.id.hs_release_date_val);
			holder.docReq = (TextView) v.findViewById(R.id.hs_doc_req);
			holder.docReqVal = (TextView) v.findViewById(R.id.hs_doc_req_val);
			v.setTag(holder);
		}else {
			holder = (Holder) v.getTag();
		}
		
		ModelHoldStatus item = (ModelHoldStatus) getItem(position);
		
		holder.eqnbrVal.setText(item.getEQ_NBR());
		
		holder.heldReason.setText("EQ NBR");
		holder.holdDate.setText("HOLD DATE");
		holder.heldReason.setText("HELD REASON");
		holder.releaseDate.setText("RELEASE DATE");
		holder.docReq.setText("DOCUMENT REQUIRED");
		
		holder.heldReasonVal.setText(item.getHELD_REASON());
		holder.holdDateVal.setText(item.getHOLD_DATE());
		holder.releaseDateVal.setText(item.getRELEASE_DATE());
		holder.docReqVal.setText(item.getDOCUMENT_REQUIRED());
		
		return v;
	}
	
	private class Holder{
		TextView eqnbrVal, heldReason, heldReasonVal, holdDate, holdDateVal,  releaseDate, releaseDateVal, docReq, docReqVal;
	}
	
}
