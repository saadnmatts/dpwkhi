package com.dpworld.androidapp.fragments;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

public class FragSelectDated extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
	    final Calendar calendar = Calendar.getInstance();
	    int yy = calendar.get(Calendar.YEAR);
	    int mm = calendar.get(Calendar.MONTH);
	    int dd = calendar.get(Calendar.DAY_OF_MONTH);
	    return new DatePickerDialog(getActivity(), this, yy, mm, dd);
    }

    public void onDateSet(DatePicker view, int yy, int mm, int dd) {
        populateSetDate(yy, mm+1, dd);
    }
    public void populateSetDate(int year, int month, int day) {
        //dob.setText(month+"/"+day+"/"+year);
    }

}