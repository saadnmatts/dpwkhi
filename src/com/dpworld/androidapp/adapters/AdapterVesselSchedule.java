package com.dpworld.androidapp.adapters;

import java.util.ArrayList;
import java.util.List;

import android.R.array;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.dpworld.androidapp.R;
import com.dpworld.androidapp.helpers.DPHelper;
import com.dpworld.androidapp.models.ModelVesselSchedule;

public class AdapterVesselSchedule extends BaseAdapter implements Filterable{
	
	Context c;
	List<ModelVesselSchedule> arrayList, toFilterList;
	LayoutInflater inflater;
	DataFilter dataFilter;
	
	public AdapterVesselSchedule(Context c, List<ModelVesselSchedule> arrayList){
		this.c = c;
		this.arrayList = arrayList;
		toFilterList = arrayList;
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
			v = li.inflate(R.layout.layout_vessel_schedule, null);
			holder = new Holder();
			holder.name = (TextView) v.findViewById(R.id.v_name);
			holder.invoyNum = (TextView) v.findViewById(R.id.v_invoy);
			holder.invoyNumVal = (TextView) v.findViewById(R.id.v_invoy_val);
			holder.virNum = (TextView) v.findViewById(R.id.v_vir_no);
			holder.virNumVal = (TextView) v.findViewById(R.id.v_vir_no_val);
			holder.eta = (TextView) v.findViewById(R.id.v_eta);
			holder.etaVal = (TextView) v.findViewById(R.id.v_eta_val);
			holder.etd = (TextView) v.findViewById(R.id.v_etd);
			holder.etdVal = (TextView) v.findViewById(R.id.v_etd_val);
			holder.ata = (TextView) v.findViewById(R.id.v_ata);
			holder.ataVal = (TextView) v.findViewById(R.id.v_ata_val);
			holder.atd = (TextView) v.findViewById(R.id.v_atd);
			holder.atdVal = (TextView) v.findViewById(R.id.v_atd_val);
			holder.cutOff = (TextView) v.findViewById(R.id.v_cut_off);
			holder.cutOffVal = (TextView) v.findViewById(R.id.v_cut_off_val);
			holder.serv = (TextView) v.findViewById(R.id.v_service);
			holder.servVal = (TextView) v.findViewById(R.id.v_service_val);
			v.setTag(holder);
		}else {
			holder = (Holder) v.getTag();
		}
		
		ModelVesselSchedule item = (ModelVesselSchedule) getItem(position);			
		
		holder.invoyNum.setText("VOYAGE");
		holder.virNum.setText("VIR");
		holder.eta.setText("ETA");
		holder.etd.setText("ETD");
		holder.ata.setText("ATA");
		holder.atd.setText("ATD");
		holder.cutOff.setText("CUT OFF");
		holder.serv.setText("SERVICE");
		
		holder.name.setText(item.getNAME());
		holder.invoyNumVal.setText(item.getIN_VOY_NBR());
		holder.virNumVal.setText(item.getVIR_NO());
		holder.etaVal.setText(item.getETA());
		holder.etdVal.setText(item.getETD());
		holder.ataVal.setText(item.getATA());
		holder.atdVal.setText(item.getATD());
		holder.cutOffVal.setText(item.getCUTOFF());
		holder.servVal.setText(item.getSERVICE());

		return v;
	}
	
	private class Holder{
		TextView name,invoyNum,invoyNumVal,virNum,virNumVal,eta,etaVal,etd,etdVal,ata,ataVal,atd,atdVal,cutOff,cutOffVal,serv,servVal;
	}
	
	private class DataFilter extends Filter{

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults results = new FilterResults();
			if (constraint != null && constraint.length() > 0) {
				List<ModelVesselSchedule> filterList = new ArrayList<ModelVesselSchedule>();
				for (int i = 0; i < toFilterList.size(); i++) {
	                if ( (toFilterList.get(i).getNAME().toUpperCase() )
	                        .contains(constraint.toString().toUpperCase())){
	                	ModelVesselSchedule item = new ModelVesselSchedule(toFilterList.get(i).getNAME(),
	                			toFilterList.get(i).getIN_VOY_NBR(),
	                			toFilterList.get(i).getVIR_NO(),
	                			toFilterList.get(i).getETA(),
	                			toFilterList.get(i).getETD(),
	                			toFilterList.get(i).getATA(),
	                			toFilterList.get(i).getATD(),
	                			toFilterList.get(i).getCUTOFF()
	                	);
	                	filterList.add(item);
	                }
				}
                results.count = filterList.size();
                results.values = filterList;				
			}else{
				results.count = toFilterList.size();
				results.values = toFilterList;
			}
			return results;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			arrayList = (List<ModelVesselSchedule>) results.values;
			notifyDataSetChanged();
		}
		
	}

	@Override
	public Filter getFilter() {
        if (dataFilter == null) {
            dataFilter = new DataFilter();
        }
        return dataFilter;
	}
	
}