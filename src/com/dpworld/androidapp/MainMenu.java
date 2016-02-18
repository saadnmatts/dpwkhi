package com.dpworld.androidapp;

import java.util.ArrayList;
import com.dpworld.androidapp.adapters.MenuGridAdapter;
import com.dpworld.androidapp.helpers.ContainerList;
import com.dpworld.androidapp.helpers.DPHelper;
import com.dpworld.androidapp.helpers.DPServices;
import com.dpworld.androidapp.models.ModelTip;
import com.dpworld.androidapp.showparsers.ShowParseAgentAuth;
import com.dpworld.androidapp.showparsers.ShowParseTipList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MainMenu extends Activity {
	
	TextView tvServResponse;
	EditText authUser, authPass, servContainNo, servCardid;
	String auser = "";
	String apass = "";
	String servCno = "";
	String servCid = "";
	
	Intent gridItemIntent = null;
	
	GridView inquiryGridView, serviceGridView, helpGridView;
	MenuGridAdapter inquiryGridAdapter, serviceGridAdapter, helpGridAdapter;
	
	String sendingTitleExtra = "";
	int sendingIconExtra;
	
	String[] inquiryOptions, serviceOptions, helpOptions;
	
	public Integer[] inquiryIcons = {
			R.drawable.ic_c_inquiry,R.drawable.ic_container,
			R.drawable.ic_bill,R.drawable.ic_hold,
			R.drawable.ic_vessel,R.drawable.ic_reefer
	};
	
	public Integer[] serviceIcons = {
			R.drawable.ic_weighment, R.drawable.ic_scan,
			R.drawable.ic_seal,R.drawable.ic_import,
			R.drawable.ic_fumigation,R.drawable.ic_sampling,
			R.drawable.ic_custom, R.drawable.ic_tip
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		
		removeAgentInfo();		
						
		gridItemIntent = new Intent(MainMenu.this,PostMenu.class);
		
		inquiryOptions = getResources().getStringArray(R.array.options_inquiry);
		inquiryGridView = (GridView) findViewById(R.id.grids_inquiry);		
		inquiryGridAdapter = new MenuGridAdapter(this, inquiryIcons, inquiryOptions);
		inquiryGridView.setAdapter(inquiryGridAdapter);
		inquiryGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				boolean isOn = DPHelper.isOn(getApplicationContext());
				if(!(isOn)){
					networkNotifyDialog();
				}else{
					sendingTitleExtra = inquiryOptions[position];
					sendingIconExtra = inquiryIcons[position];
					postMenu(sendingTitleExtra,sendingIconExtra);					
				}
			}
		});
		
		serviceOptions = getResources().getStringArray(R.array.options_services);
		serviceGridView = (GridView) findViewById(R.id.grids_service);
		serviceGridAdapter = new MenuGridAdapter(this, serviceIcons, serviceOptions);
		serviceGridView.setAdapter(serviceGridAdapter);
		serviceGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				if(callAgentId().equals("")){
					authenticatePassDialog();
				}else{
					if(serviceOptions[position].toString().equals("Update Truck No")){
						DoTipSync startTask = new DoTipSync();
						startTask.execute();						
					}else{
						createDialog(serviceOptions[position]);
					}
				}				
			}
		});
			
	}
	
	void hidekeysoft(){
		View view = this.getCurrentFocus();
		if (view != null) {  
		    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}
	
	void postMenu(String sendingTitle, int sendingIcon){
		gridItemIntent.putExtra(DPHelper.INTENT_EXTRA_TITLE, sendingTitle);
		gridItemIntent.putExtra(DPHelper.INTENT_EXTRA_ICON, sendingIcon);
		startActivity(gridItemIntent);		
	}
	
	void postMenu(String sendingTitle){
		gridItemIntent.putExtra(DPHelper.INTENT_EXTRA_TITLE, sendingTitle);
		startActivity(gridItemIntent);
	}
	
	void networkNotifyDialog(){
		AlertDialog.Builder builder = new AlertDialog.Builder(MainMenu.this);
		builder.setTitle(getResources().getString(R.string.text_warning))
			.setMessage(getResources().getString(R.string.text_netcon_error))
			.setNegativeButton(R.string.text_dismiss, null);	
		AlertDialog dialog = builder.create();
		dialog.show();		
	}
	
	void createDialog(String title){
		final String items[] = getsubservice(title);
		AlertDialog.Builder builder = new AlertDialog.Builder(MainMenu.this);
		builder.setTitle(title)
			.setNegativeButton(R.string.text_cancel, null)	
			.setItems(items,new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int which) {	            	   
	            	   String toOperate = getServiceName(items[which]);
	            	   if(toOperate.equals("") || toOperate.equals(null)){	            		   
	            	   }else{
	            		   serviceReqDialog(toOperate, items[which]);
	            	   }
	               }
	        });
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	void serviceReqDialog(final String serviceID, final String serviceName){
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    final View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_service_input, null);
	    builder.setTitle("Enter Details");
	    builder.setView(view);
	    servContainNo = (EditText) view.findViewById(R.id.etContainerNo);
	    builder.setPositiveButton(R.string.text_okay, new OnClickListener() {			
			@Override
			public void onClick(DialogInterface dialog, int which) {
			    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);				
				servCno = DPHelper.trimString(servContainNo.getText().toString());
				confirmationDialog(serviceName, serviceID);
			}
		});
	    builder.setNegativeButton(R.string.text_cancel, null);
	    final Dialog dialog;
	    dialog = builder.create();
	    dialog.show();
	}
	
	void serviceResponseDialog(String repsonsetxt){
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    final View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_service_response, null);
	    builder.setView(view);
	    final Dialog dialog;
	    tvServResponse = (TextView) view.findViewById(R.id.tvSerResponse);
	    tvServResponse.setText(repsonsetxt);
	    builder.setNegativeButton(R.string.text_okay, null);
	    dialog = builder.create();
	    dialog.show();		
	}
	
	void authenticatePassDialog(){
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    final View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_auth_pass, null);
	    builder.setView(view);
	    final Dialog dialog;
	    authUser  = (EditText) view.findViewById(R.id.etAgentUser);
	    authPass = (EditText) view.findViewById(R.id.etAgentPass);
	    builder.setTitle("Authenticate");
	    builder.setPositiveButton(R.string.text_send,new android.content.DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int arg1) {
		        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);		    	
		    	auser = DPHelper.trimString(authUser.getText().toString());
		    	apass = DPHelper.trimString(authPass.getText().toString());
		    	if(auser.equals("") && apass.equals("")){
		    		DPHelper.longToast(getApplicationContext(), "Fill in the Required Fields.");
		    	}else if(!(auser.equals("") && apass.equals(""))){
		    		String _cryptPass = DPHelper.trimString(DPHelper.crypt(apass));
					DoAsync startTask = new DoAsync();
					startTask.execute(auser,_cryptPass);
		    	}		    	
		    }                  
	    });
	    builder.setNegativeButton(R.string.text_cancel,	null);
	    dialog = builder.create();
	    dialog.show();		
	}
	
	void confirmationDialog(final String serviceName, final String servicID){
		String messageDialog = getResources().getString(R.string.text_service_confirm) + " " + serviceName + " ?";
		AlertDialog.Builder builder = new AlertDialog.Builder(MainMenu.this);
		builder.setTitle(getResources().getString(R.string.text_confirm));
		builder.setMessage(messageDialog);
		builder.setPositiveButton(R.string.text_yes, new OnClickListener() {			
			@Override
			public void onClick(DialogInterface di, int which) {
				ServiceSync startTask = new ServiceSync();
				startTask.execute(servCno,servicID);				
			}
		});
		builder.setNegativeButton(R.string.text_no, new OnClickListener() {			
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});		
		AlertDialog dialog = builder.create();
		dialog.show();
	}	
	
	String getServiceName(String s){
		String result = "";
		if(s.equals("On Customer Truck")){
			result = DPHelper.WEIGHMENT_WEIGHMENT;
		}else if(s.equals("On QICT Truck")){
			result = DPHelper.WEIGHMENT_WEIGH;
		}else if(s.equals("Scan on Customer Track")){
			result = DPHelper.SCAN_SCAN_CT;
		}else if(s.equals("Scan on QICT Truck")){
			result = DPHelper.SCAN_SCAN;
		}else if(s.equals("Grounded For Seal Verification")){
			result = DPHelper.SEAL_VARIFICATION;
		}else if(s.equals("Without seal Break")){
			result = DPHelper.FUMIGATION_FUMIGATE;
		}else if(s.equals("With Seal Break")){
			result = DPHelper.FUMIGATION_SEAL_BREAK;
		}else if(s.equals("Hot Sampling")){
			result = DPHelper.SAMPLING_HOTWOK_EXM;
		}else if(s.equals("PPRO sampling")){
			result = DPHelper.SAMPLING_SAMPLE;
		}else if(s.equals("Import ANF Examination")){
			result = DPHelper.IMP_ANF;
		}else if(s.equals("5% EXAMINATION (Seal Break Partial Devanning Revaning)")){
			result = DPHelper.ON_CUST_FIVE;
		}else if(s.equals("100% EXAMINATION (Full Devan Revan)")){
			result = DPHelper.ON_CUST_HUND;
		}
		return result;
	}	
	
	String[] getsubservice(String title){
		String items[] = {};
		if(title.equals("Weightment")){
			items = getResources().getStringArray(R.array.weighment);
		}else if(title.equals("Scan")){
			items = getResources().getStringArray(R.array.scan);
		}else if(title.equals("Seal Verification")){
			items = getResources().getStringArray(R.array.seal_verification);
		}else if(title.equals("Fumigation")){
			items = getResources().getStringArray(R.array.fumigation);
		}else if(title.equals("Sampling")){
			items = getResources().getStringArray(R.array.sampling);
		}else if(title.equals("Import Examination")){
			items = getResources().getStringArray(R.array.import_anf_examination);
		}else if(title.equals("One Custom Examination")){
			items = getResources().getStringArray(R.array.on_custom_examination);
		}
		return items;
	}
	
	void createListinDialog(){		
	}

	public void removeAgentInfo(){
		SharedPreferences sharedPref = getSharedPreferences(DPHelper.USER_INFO_PREF, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.remove(DPHelper.AGENT_ID);
		editor.remove(DPHelper.CARD_ID);
		editor.apply();
	}
	
	public void saveUserInfo(String retAgentId, String retCardId){		
		SharedPreferences sharedPref = getSharedPreferences(DPHelper.USER_INFO_PREF, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putString(DPHelper.CARD_ID, retCardId);
		editor.putString(DPHelper.AGENT_ID, retAgentId);
		editor.apply();
	}
	
	public String callAgentId(){
		SharedPreferences sharedPref = getSharedPreferences(DPHelper.USER_INFO_PREF, Context.MODE_PRIVATE);
		String idAgent = sharedPref.getString(DPHelper.AGENT_ID, "");
		return idAgent;
	}
	
	public String callCardId(){
		SharedPreferences sharedPref = getSharedPreferences(DPHelper.USER_INFO_PREF, Context.MODE_PRIVATE);
		String idCard = sharedPref.getString(DPHelper.CARD_ID, "");
		return idCard;		
	}
	
	void assignDataUser(ArrayList<String> a){
		String agentid = a.get(0).toString();
		String cardid = a.get(1).toString();
		if(agentid.equals("") || cardid.equals("")){
			DPHelper.quickToast(getBaseContext(), "Authentication Failed.");
		}else{		
			saveUserInfo(agentid, cardid);
			DPHelper.quickToast(getBaseContext(), "Authenticated.");
		}			
	}
	
	void tipListUpdateTruck(final ArrayList<ModelTip> jTipList, int position){
		String alertTitle = "Update Truck Number";
		AlertDialog.Builder builder = new AlertDialog.Builder(MainMenu.this);
	    final View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_updatetruck, null);
	    builder.setView(view);
		builder.setTitle(alertTitle);
		builder.setPositiveButton(R.string.text_update, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				EditText etTruckNo = (EditText) findViewById(R.id.et_update_truck);
				String truckNo  = etTruckNo.getText().toString();
				
			}
		});
		builder.setNegativeButton(R.string.text_cancel, null);
		AlertDialog dialog = builder.create();
		dialog.show();			
	}
	
	void tipListDialog(final ArrayList<ModelTip> jTipList){
		final ArrayList<String> itemsList = new ArrayList<String>();
		for (int i = 0; i < jTipList.size(); i++){
			itemsList.add(jTipList.get(i).getCTR_NBR().toString());
		}
		
		String[] items = new String[jTipList.size()];
		itemsList.toArray(items);
		
		String alertTitle = "Container Numbers";
		AlertDialog.Builder builder = new AlertDialog.Builder(MainMenu.this);
		builder.setTitle(alertTitle);
		builder.setItems(items, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				tipListUpdateTruck(jTipList,which);
			}
		});
		builder.setNegativeButton(R.string.text_cancel, null);		
		AlertDialog dialog = builder.create();
		dialog.show();		
	}
	
	private class DoTipSync extends AsyncTask<String, String, String>{

		@Override
		protected String doInBackground(String... params) {
			Log.i(DPHelper.LOGTAG, "In Background.");
			String result = "";
			try {
				String tipList = DPServices.tipList(callAgentId());
				result = tipList;
			} catch (Exception e) {
			}			
			return result;
		}
		
		@Override
		protected void onPostExecute(String result) {
			if(result.equals("[]") || (result.equals(""))){
				DPHelper.longToast(getApplicationContext(), DPHelper.SERVICE_NOT_FOUND_ERROR);
				serviceGridView.setEnabled(true);
			}else{
				ShowParseTipList tl = new ShowParseTipList(getBaseContext(), result);
				ArrayList<ModelTip> jTipList = new ArrayList<ModelTip>();
				jTipList = tl.onDataLoadedTipList();
				ContainerList.getInstance().set(jTipList);
				serviceGridView.setEnabled(true);
				postMenu("Tip List");
			}			
		}
		
	}	
	
	private class ServiceSync extends AsyncTask<String, String, String>{
		@Override
		protected void onPreExecute() {
			serviceGridView.setEnabled(false);
			DPHelper.longToast(getBaseContext(), "Request is being processed.");
		}
		@Override
		protected String doInBackground(String... params) {
			String savedAgentId = callAgentId();
			String savedCardId = callCardId();
			String result = "";
			try {
				result = DPServices.serviceRequest(params[1], params[0], savedAgentId, savedCardId);			
			} catch (Exception e) {
			}
			return result;
		}
		@Override
		protected void onPostExecute(String result) {
			String sendResponse = "";
			if(result.equals("OK") || result.equals("ok") || result.equals("Ok")){
				sendResponse = getResources().getString(R.string.text_service_success);
				serviceResponseDialog(sendResponse);
			}else {
				sendResponse = getResources().getString(R.string.text_service_fail);
				serviceResponseDialog(sendResponse);
			}
			serviceGridView.setEnabled(true);
		}
		
	}
	
	private class DoAsync extends AsyncTask<String, String, String>{		
		@Override
		protected void onPreExecute() {		
			serviceGridView.setEnabled(false);
			DPHelper.longToast(getBaseContext(), "Authentication in Progress ...");
		}		
		@Override
		protected String doInBackground(String... params) {
			String result = "";
			try {
				result = DPServices.authenticatePassword(params[0], params[1]);
			} catch (Exception e) {
			}			
			return result;
		}
		@Override
		protected void onPostExecute(String result) {
			Log.i(DPHelper.LOGTAG, "I am a result: "+ result.toString());
			if(result.equals("[]") || (result.equals(""))){
				DPHelper.longToast(getApplicationContext(), DPHelper.SERVICE_NOT_FOUND_ERROR);
				serviceGridView.setEnabled(true);
			}else{
				ShowParseAgentAuth aa = new ShowParseAgentAuth(getBaseContext(), result);
				ArrayList<String> ageCard = new ArrayList<String>();
				ageCard =  aa.onDataLoadedAuthPass();
				assignDataUser(ageCard);
				serviceGridView.setEnabled(true);				
			}
		}
	}
	
}
