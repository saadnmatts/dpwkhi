package com.dpworld.androidapp.fragments;

import com.dpworld.androidapp.R;
import com.dpworld.androidapp.helpers.DPHelper;
import com.dpworld.androidapp.helpers.DPServices;
import com.dpworld.androidapp.models.ModelReleaseStatus;
import com.dpworld.androidapp.models.ModelHoldStatus;
import com.dpworld.androidapp.models.ModelReeferInquiry;
import com.dpworld.androidapp.showparsers.ShowParseContainerS;
import com.dpworld.androidapp.showparsers.ShowParseHold;
import com.dpworld.androidapp.showparsers.ShowParseReefer;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class FragContainerHoldReefer extends Fragment {
	
	Bundle getExtras;
	TextView fragTitle, headtitle;
	Button btnSearch;
	EditText searchContainerNo;
	String retainedContainerNo, taskName;
	ProgressBar waitSpinner;
	TableLayout dataTable;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.frag_container_hold_reefer, container,false);
		
		getExtras = getArguments();
		
		headtitle = (TextView) rootView.findViewById(R.id.heading_title);
		headtitle.setText(getTitleForAppBar());			
		
		dataTable = (TableLayout) rootView.findViewById(R.id.table_container_status);
		
		waitSpinner = (ProgressBar) rootView.findViewById(R.id.progressBar);
		waitSpinner.setVisibility(View.GONE);
		
		searchContainerNo = (EditText) rootView.findViewById(R.id.search_container_no);
		
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
					dataTable.removeAllViews();
					DoAsync startTask = new DoAsync();
					startTask.execute(retainedContainerNo.toUpperCase());
					waitSpinner.setVisibility(View.VISIBLE);
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
			btnSearch.setEnabled(false);
		}
		
		@Override
		protected String doInBackground(String... params) {
			taskName = getTitleForAppBar();
			String result = "";
			try {
				if(taskName.equals("Release Status")){					
					result = DPServices.containerStatus(params[0], DPHelper.MAIN_USERNAME, DPHelper.MAIN_PASSWORD);
				}else if(taskName.equals("Reefer Inquiry")){
					result = DPServices.reeferInquiry(params[0], DPHelper.MAIN_USERNAME, DPHelper.MAIN_PASSWORD);
				}					
			} catch (Exception e) {
			}			
			return result;
		}
		@Override
		protected void onPostExecute(String result) {			
			if(!(result.equals(null) || result.equals(""))){
				waitSpinner.setVisibility(View.GONE);
				if(taskName.equals("Release Status")){					
					ShowParseContainerS parseCs= new ShowParseContainerS(getActivity(), result);
					ModelReleaseStatus getJo = parseCs.onDataLoadedContainerStatus();
					displayDataContainerStatus(getJo);	
				}else if(taskName.equals("Reefer Inquiry")){
					ShowParseReefer parseReefer = new ShowParseReefer(getActivity(), result);					
					ModelReeferInquiry getJo = parseReefer.onDataLoadedReeferInquiry();
					displayDataReeferInquiry(getJo);
				}
				btnSearch.setEnabled(true);				
			}
		}
	}

	void displayDataReeferInquiry(ModelReeferInquiry object){
		if(object == null){	
			DPHelper.longToast(getActivity(), "Data is not found for this Container Number. \n Try Entering Correct Container Number.");
		}
		else{
			drawTableReeferInquiry(object);
		}
	}
	
	void drawTableReeferInquiry(ModelReeferInquiry obj){
		drawTableRow("NBR", obj.getNBR());
		drawTableRow("SET TEMPERATURE", obj.getSET_TEMP());
		drawTableRow("CURRENT TEMPERATURE", obj.getCURRENT_TEMP());
		drawTableRow("VENT", obj.getVENT());
		drawTableRow("HUMIDITY", obj.getHUMIDITY_REQUIRED());
		drawTableRow("COMMODITY DESCRIPTION", obj.getCOM_DES());
		drawTableRow("CATEGORY", obj.getCATEGORY());
	}	
		
	void displayDataHoldStatus(ModelHoldStatus object){
		if(object == null){	
			DPHelper.longToast(getActivity(), "Data is not found for this Container Number. \n Try Entering Correct Container Number.");
		}
		else{
			drawTableHoldStatus(object);
		}
	}
	
	void drawTableHoldStatus(ModelHoldStatus obj){
		drawTableRow("EQ NBR", obj.getEQ_NBR());
		drawTableRow("HELD REASON", obj.getHELD_REASON());
		drawTableRow("HOLD DATE", obj.getHOLD_DATE());
		drawTableRow("RELEASE DATE", obj.getRELEASE_DATE());
		drawTableRow("DOCUMENT REQUIRED", obj.getDOCUMENT_REQUIRED());
	}	
	
	@SuppressWarnings("deprecation")
	void displayDataContainerStatus(ModelReleaseStatus object){
		if(object == null){	
			DPHelper.longToast(getActivity(), "Data is not found for this Container Number. \n Try Entering Correct Container Number.");
		}
		else{
			drawTableContainerStatus(object);
		}
	}
	
	void drawTableContainerStatus(ModelReleaseStatus obj){
		drawTableRow("EQ NBR", obj.getEQ_NBR());
		drawTableRow("GROUP ID", obj.getGROUP_ID());
		drawTableRow("LOCATION", obj.getLOCATION());
		drawTableRow("DECLARED WEIGHT", obj.getWEIGHT());
		drawTableRow("SCAN STATUS", obj.getSCAN_STATUS());
		drawTableRow("ANF STATUS", obj.getANF_STATUS());
	}
	
	void drawTableRow(String colLeft, String colRight){
		TableRow trow = new TableRow(getActivity());
		trow.setWeightSum(1f);
		trow.setBackgroundColor(getResources().getColor(R.color.dark_blue));
		trow.addView(DPHelper.drawTableLeftColumn(getActivity(), colLeft));
		trow.addView(DPHelper.drawTableRightColumn(getActivity(), colRight));
		dataTable.addView(trow);		
	}

}
