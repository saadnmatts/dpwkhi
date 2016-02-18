package com.dpworld.androidapp.fragments;

import java.util.ArrayList;

import com.dpworld.androidapp.MainMenu;
import com.dpworld.androidapp.R;
import com.dpworld.androidapp.adapters.AdapterTipList;
import com.dpworld.androidapp.helpers.ContainerList;
import com.dpworld.androidapp.helpers.DPHelper;
import com.dpworld.androidapp.helpers.DPServices;
import com.dpworld.androidapp.models.ModelTip;
import com.dpworld.androidapp.showparsers.ShowParseTipList;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FragTipList extends Fragment {
	Bundle getExtras;
	TextView fragTitle, headtitle;
	ListView dataView;
	AdapterTipList adapter;
	String truckNo;
	String containerNo, gKey;
	int itemPos;
//	Double gKey;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		Log.i(DPHelper.LOGTAG, "In OncreateView of Fragment.");
		
		View rootView = inflater.inflate(R.layout.frag_tip_list, container,false);
		
		getExtras = getArguments();
		
		headtitle = (TextView) rootView.findViewById(R.id.heading_title);
		headtitle.setText("Containers List");	
		
		dataView = (ListView) rootView.findViewById(R.id.listViewTipList);
		
		createDataList();
		
		return rootView;
	}
	
	String getTitleForAppBar(){
		return getExtras.getString(DPHelper.INTENT_EXTRA_TITLE);
	}
	
	void createDataList(){
		Log.i(DPHelper.LOGTAG, "I am in create Data List.");
		Log.i(DPHelper.LOGTAG, ContainerList.getInstance().get().toString());
		adapter = new AdapterTipList(getActivity(), ContainerList.getInstance().get());
		dataView.setAdapter(adapter);
		dataView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				tipListUpdateTruck(ContainerList.getInstance().get(), position);
			}
		});
	}
	
	void tipListUpdateTruck(final ArrayList<ModelTip> jTipList, final int position){
		String alertTitle = "Update Truck Number";
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    final View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_updatetruck, null);
	    builder.setView(view);
		builder.setTitle(alertTitle);
		builder.setPositiveButton(R.string.text_update, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				EditText etTruckNo = (EditText) view.findViewById(R.id.et_update_truck);
				truckNo  = etTruckNo.getText().toString();
				containerNo = jTipList.get(position).getCTR_NBR().toString();
				gKey = jTipList.get(position).getGKEY();
				itemPos = position;
				
//				Log.i(DPHelper.LOGTAG, truckNo + " " + containerNo + " " + Double.toString(gKey));
				UpdateTruck ut = new UpdateTruck();
				ut.execute();
			}
		});
		builder.setNegativeButton(R.string.text_cancel, null);
		AlertDialog dialog = builder.create();
		dialog.show();			
	}
	
	private class UpdateTruck extends AsyncTask<String, String, String>{

		@Override
		protected String doInBackground(String... params) {
			String result = "";
			try {
				String tipList = DPServices.updateTruck(gKey, containerNo, truckNo);
				result = tipList;
			} catch (Exception e) {
			}			
			return result;
		}
		
		@Override
		protected void onPostExecute(String result) {
			if(result.equals("[]") || (result.equals(""))){
				DPHelper.longToast(getActivity(), DPHelper.SERVICE_NOT_FOUND_ERROR);
			}else{
				Log.i(DPHelper.LOGTAG, result);
				ContainerList.getInstance().addTruckNumber(truckNo, itemPos);
				createDataList();
				Toast.makeText(getActivity(), "UPDATED", Toast.LENGTH_SHORT).show();
			}			
		}
		
	}
	
}
