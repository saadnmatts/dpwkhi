package com.dpworld.androidapp.fragments;

import java.util.ArrayList;
import java.util.List;

import com.dpworld.androidapp.R;
import com.dpworld.androidapp.adapters.AdapterVesselSchedule;
import com.dpworld.androidapp.helpers.DPHelper;
import com.dpworld.androidapp.helpers.DPServices;
import com.dpworld.androidapp.models.ModelVesselSchedule;
import com.dpworld.androidapp.showparsers.ShowParseVesselSchedule;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.sax.RootElement;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class FragVesselSchedule extends Fragment{
	
	Bundle getExtras;
	TextView fragTitle, headtitle;
	Button btnSearch,btnLoadData;
	EditText searchContainerNo;
	String retainedContainerNo, taskName;
	ProgressBar indicatorProgress;
	ListView dataView;
	AdapterVesselSchedule adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.frag_vessel_schedule, container,false);
		
		getExtras = getArguments();
		
		headtitle = (TextView) rootView.findViewById(R.id.heading_title);
		headtitle.setText(getTitleForAppBar());	
		
		searchContainerNo = (EditText) rootView.findViewById(R.id.search_container_no);
		searchContainerNo.setEnabled(false);
		
		dataView = (ListView) rootView.findViewById(R.id.listViewVesselSchedule);
				
		btnLoadData = (Button) rootView.findViewById(R.id.loadVesselsSchedules);
		btnLoadData.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View view) {
			    final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			    imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);				
				if(searchContainerNo.getText().toString().equals("")){
					loadDataVesselSchedule();
				}else{
					searchContainerNo.setText("");						
					loadDataVesselSchedule();
				}
			}
		});
		
		searchContainerNo.addTextChangedListener(new TextWatcher() {			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				adapter.getFilter().filter(s);
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		
		return rootView;
	}
	
	void loadDataVesselSchedule(){	
		DoAsync startTask = new DoAsync();
		startTask.execute();		
	}
	
	String getTitleForAppBar(){
		return getExtras.getString(DPHelper.INTENT_EXTRA_TITLE);
	}
	
	private class DoAsync extends AsyncTask<Void, String, String>{
		
		@Override
		protected void onPreExecute() {
			btnLoadData.setEnabled(false);
		}
		
		@Override
		protected String doInBackground(Void... params) {
			taskName = getTitleForAppBar();
			String result = "";
			try {
				result = DPServices.vesselSchedule(DPHelper.MAIN_USERNAME, DPHelper.MAIN_PASSWORD);
			} catch (Exception e) {
			}			
			return result;
		}
		@Override
		protected void onPostExecute(String result) {
			Log.i(DPHelper.LOGTAG, result);
			btnLoadData.setEnabled(true);
			if(!(result.equals(null) || result.equals(""))){
				ShowParseVesselSchedule parseVs = new ShowParseVesselSchedule(getActivity(), result);
				List<ModelVesselSchedule> getJo = new ArrayList<ModelVesselSchedule>();
				getJo = parseVs.onDataLoadedVesselSchedule();
				displayDataVesselSchedule(getJo);
			}				
		}
		
		void displayDataVesselSchedule(List<ModelVesselSchedule> objectsArray){
			if(objectsArray.isEmpty()){
				DPHelper.longToast(getActivity(), "Data is not found for this Container Number. \n Try Entering Correct Container Number.");
			}else{
				adapter = new AdapterVesselSchedule(getActivity(), objectsArray);
				dataView.setAdapter(adapter);
				searchContainerNo.setEnabled(true);
			}			
		}
	}	
}
