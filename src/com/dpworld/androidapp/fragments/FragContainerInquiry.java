package com.dpworld.androidapp.fragments;

import java.util.List;
import com.dpworld.androidapp.R;
import com.dpworld.androidapp.adapters.AdapterContainerInquiry;
import com.dpworld.androidapp.helpers.DPHelper;
import com.dpworld.androidapp.helpers.DPServices;
import com.dpworld.androidapp.models.ModelContainerInquiry;
import com.dpworld.androidapp.showparsers.ShowParseContainerInquiry;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class FragContainerInquiry extends Fragment {
	
	Bundle getExtras;
	TextView fragTitle, headtitle;
	Button btnSearch;
	EditText searchContainerNo;
	String retainedContainerNo, taskName;
	ProgressBar indicatorProgress;
	ListView dataView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.frag_container_inquiry, container,false);
		
		getExtras = getArguments();
		
		headtitle = (TextView) rootView.findViewById(R.id.heading_title);
		headtitle.setText(getTitleForAppBar());	
		
		searchContainerNo = (EditText) rootView.findViewById(R.id.search_container_no);
		
		dataView = (ListView) rootView.findViewById(R.id.listViewContainerInquiry);
		
		btnSearch = (Button) rootView.findViewById(R.id.btn_search);
		btnSearch.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View view) {
			    final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			    imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);				
				retainedContainerNo = DPHelper.trimString(searchContainerNo.getText().toString());
				if(retainedContainerNo.equals("") || retainedContainerNo.equals(" ")){
					DPHelper.quickToast(getActivity(), DPHelper.EMPTY_FIELD);
				}
				else{
					btnSearch.setEnabled(false);
					DoAsync startTask = new DoAsync();
					startTask.execute(retainedContainerNo.toUpperCase());	
				}
			}
		});		
		
		return rootView;		
	}
	
	String getTitleForAppBar(){
		return getExtras.getString(DPHelper.INTENT_EXTRA_TITLE);
	}
	
	private class DoAsync extends AsyncTask<String, String, String>{
		
		@Override
		protected void onPreExecute() {	
			searchContainerNo.setEnabled(false);
		}
		
		@Override
		protected String doInBackground(String... params) {
			taskName = getTitleForAppBar();
			String result = "";
			try {
				result = DPServices.containerInquiry(params[0], DPHelper.MAIN_USERNAME, DPHelper.MAIN_PASSWORD);
			} catch (Exception e) {
			}			
			return result;
		}
		@Override
		protected void onPostExecute(String result) {
			searchContainerNo.setEnabled(true);
			if(!(result.equals(null) || result.equals(""))){
				ShowParseContainerInquiry parseCi = new ShowParseContainerInquiry(getActivity(), result);
				List<ModelContainerInquiry> getJo = parseCi.onDataLoadedContainerInquiry();
				displayDataContainerInquiry(getJo);
				btnSearch.setEnabled(true);
			}				
		}
	}
	
	void displayDataContainerInquiry(List<ModelContainerInquiry> objectsArray){
		if(objectsArray.isEmpty()){
			DPHelper.longToast(getActivity(), "Data is not found for this Container Number. \n Try Entering Correct Container Number.");
		}else{
			AdapterContainerInquiry adapter = new AdapterContainerInquiry(getActivity(), objectsArray);
			dataView.setAdapter(adapter);
		}
	}
	
}
