package com.dpworld.androidapp;

import com.dpworld.androidapp.fragments.FragContainerHoldReefer;
import com.dpworld.androidapp.fragments.FragContainerInquiry;
import com.dpworld.androidapp.fragments.FragHoldStatus;
import com.dpworld.androidapp.fragments.FragInvoiceInquiry;
import com.dpworld.androidapp.fragments.FragVesselSchedule;
import com.dpworld.androidapp.helpers.DPHelper;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class PostMenu extends Activity {	
	
	Bundle extras = null;
	
	Intent incomingIntent;
	String title_activity = "";
	int icon = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post_menu);
		customAppBar();
		addlayout(title_activity);
	}
	
	@Override
	public void onBackPressed() {
		finish();
	}
	
	void addlayout(String title_name){
		
		if(title_name.equals("Release Status") || title_name.equals("Reefer Inquiry")){
			containerHoldReefer();
		}else if(title_name.equals("Container Inquiry")){
			containerInquiry();
		}else if(title_name.equals("Vessel Schedule")){
			vesselSchedule();
		}else if(title_name.equals("Billing Invoice")){
			billingInvoice();
		}else if(title_name.equals("Hold Status")){
			holdStatus();
		}
		
	}
	
	void customAppBar(){
		incomingIntent = getIntent();
		extras = incomingIntent.getExtras();
		if(extras != null){
			title_activity = getTitleForAppBar();
			icon = getIconForAppBar();
			setAppbar();
		}		
	}
	
	void setAppbar(){				
		setTitle(getResources().getString(R.string.app_name));
		getActionBar().setDisplayHomeAsUpEnabled(true);
		//getActionBar().setIcon(android.R.color.transparent);
	}
	
	String getTitleForAppBar(){
		return incomingIntent.getStringExtra(DPHelper.INTENT_EXTRA_TITLE);
	}
	
	int getIconForAppBar(){
		return incomingIntent.getIntExtra(DPHelper.INTENT_EXTRA_ICON,-1);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case android.R.id.home:
	        NavUtils.navigateUpFromSameTask(this);
	        return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	
	void containerHoldReefer(){
		FragContainerHoldReefer fragment = new FragContainerHoldReefer();
		fragment.setArguments(extras);
		FragmentManager manager = getFragmentManager();
		manager.beginTransaction()
        .add(R.id.frame_post_menu, fragment).commit();		
	}
	
	void containerInquiry(){
		FragContainerInquiry fragment = new FragContainerInquiry();
		fragment.setArguments(extras);
		FragmentManager manager = getFragmentManager();
		manager.beginTransaction()
        .add(R.id.frame_post_menu, fragment).commit();			
	}
	
	void vesselSchedule(){
		FragVesselSchedule fragment = new FragVesselSchedule();
		fragment.setArguments(extras);
		FragmentManager manager = getFragmentManager();
		manager.beginTransaction()
        .add(R.id.frame_post_menu, fragment).commit();		
	}
	
	void billingInvoice(){
		FragInvoiceInquiry fragment = new FragInvoiceInquiry();
		fragment.setArguments(extras);
		FragmentManager manager = getFragmentManager();
		manager.beginTransaction()
        .add(R.id.frame_post_menu, fragment).commit();	
	}
	
	void holdStatus(){
		FragHoldStatus fragment = new FragHoldStatus();
		fragment.setArguments(extras);
		FragmentManager manager = getFragmentManager();
		manager.beginTransaction()
        .add(R.id.frame_post_menu, fragment).commit();			
	}

}
