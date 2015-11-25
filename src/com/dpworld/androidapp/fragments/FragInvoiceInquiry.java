package com.dpworld.androidapp.fragments;

import java.util.Calendar;

import com.dpworld.androidapp.R;
import com.dpworld.androidapp.helpers.DPHelper;
import com.dpworld.androidapp.helpers.DPServices;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class FragInvoiceInquiry extends Fragment{
	
	Bundle getExtras;
	TextView fragTitle, headtitle;
	Button btnSearch, btnpickDate;
	EditText etBillNo, etPickdate;
	String retBillNo, deliveryDate;
	TableLayout dataTable;
	ProgressBar waitSpinner;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.frag_invoice_inquiry, container,false);
			
		getExtras = getArguments();		
		headtitle = (TextView) rootView.findViewById(R.id.heading_title);
		headtitle.setText(getTitleForAppBar());
		
		dataTable = (TableLayout) rootView.findViewById(R.id.table_invoice_details);
		
		deliveryDate = "";
		waitSpinner = (ProgressBar) rootView.findViewById(R.id.waitSpin);
		waitSpinner.setVisibility(View.GONE);
		
		etBillNo = (EditText) rootView.findViewById(R.id.et_billno);
		etPickdate = (EditText) rootView.findViewById(R.id.et_pickdate);
		etPickdate.setInputType(InputType.TYPE_NULL);
		
		etPickdate.setOnFocusChangeListener(new OnFocusChangeListener() {			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
				    final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
				    imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
					DialogFragment newFragment = new FragSelectDate();
					newFragment.show(getFragmentManager(), "DatePicker");
					etPickdate.clearFocus();
				}
			}
		});
		
		btnSearch = (Button) rootView.findViewById(R.id.btn_search);

		btnSearch.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
			    final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			    imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);				
				dataTable.removeAllViews();
				retBillNo = DPHelper.trimString(etBillNo.getText().toString());
				if(retBillNo.equals("")){
					etBillNo.setError("Required.");
				}else if(deliveryDate.equals("")){
					etPickdate.setError("Select Delivery Date.");
				}else{
					etBillNo.setError(null);
					etPickdate.setError(null);
					DoAsync startTask = new DoAsync();
					startTask.execute(retBillNo);
					waitSpinner.setVisibility(View.VISIBLE);
				}
			}
		});
	
		return rootView;
	}

	/* Draws each row in a a table*/
	void drawTableRow(String colLeft, String colRight){
		TableRow trow = new TableRow(getActivity());
		trow.setBackgroundColor(getResources().getColor(R.color.dark_blue));
		trow.addView(DPHelper.drawTableLeftColumn(getActivity(), colLeft));
		trow.addView(DPHelper.drawTableRightColumn(getActivity(), colRight));
		dataTable.addView(trow);		
	}	
	
	String getTitleForAppBar(){
		return getExtras.getString(DPHelper.INTENT_EXTRA_TITLE);
	}
	
	void displayInquiryData(String toShow){
		if(toShow.equals("")){
			DPHelper.quickToast(getActivity(), "Try Again.");
		}else{
			drawTableRow(getResources().getString(R.string.text_billno), retBillNo.toString());
			drawTableRow("Delivery Date", deliveryDate);
			drawTableRow("Invoice Amount", toShow.toString());
		}
	}
	
	private class DoAsync extends AsyncTask<String, String, String>{
		
		@Override
		protected void onPreExecute() {
			btnSearch.setEnabled(false);
			etBillNo.setEnabled(false);
			getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
			DPHelper.longToast(getActivity(), "The Request is being processed.");
		}
		
		@Override
		protected String doInBackground(String... params) {
			String result = DPServices.invoiceInquiry(params[0], deliveryDate, DPHelper.MAIN_USERNAME, DPHelper.MAIN_PASSWORD);
			return result;
		}
		@Override
		protected void onPostExecute(String result) {			
			displayInquiryData(result);
			btnSearch.setEnabled(true);
			waitSpinner.setVisibility(View.GONE);
			etBillNo.setEnabled(true);
		}
	}
	
	public class FragSelectDate extends DialogFragment implements DatePickerDialog.OnDateSetListener {

	    @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	    	deliveryDate = "";
	    	etPickdate.setText(deliveryDate);
	    	
		    final Calendar calendar = Calendar.getInstance();
		    int yy = calendar.get(Calendar.YEAR);
		    int mm = calendar.get(Calendar.MONTH);
		    int dd = calendar.get(Calendar.DAY_OF_MONTH);
		    return new DatePickerDialog(getActivity(), this, yy, mm, dd);
	    }
	    
	    @Override
	    public void dismiss() {	    	
	    	deliveryDate = "";	    	
	    	etPickdate.setText(deliveryDate);
	    	etPickdate.clearFocus();	    	
	    }	   
	    
	    public void onDateSet(DatePicker view, int yy, int mm, int dd) {
	        populateSetDate(yy, mm+1, dd);
	    }
	    public void populateSetDate(int year, int month, int day) {	    		    	
	    	String yyyy = Integer.toString(year);
	    	String mm = Integer.toString(month);
	    	String dd = Integer.toString(day);
	    	
	    	deliveryDate = yyyy+"-"+mm+"-"+dd;	    	
	    	etPickdate.setText(deliveryDate);
	    	etPickdate.clearFocus();
	    }

	}
	
}
