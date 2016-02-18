package com.dpworld.androidapp.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.dpworld.androidapp.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint.Style;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public final class DPHelper {
	
	public static final String LOGTAG = "test";
	
	public static final String INTENT_EXTRA_TITLE = "activity_title";
	public static final String INTENT_EXTRA_ICON = "activity_icon";
	public static final String INTENT_EXTRA_CONTAINER_LIST = "container_list";
	public static final String MAIN_USERNAME = "MATS";
	public static final String MAIN_PASSWORD = "abc123";
	
	public static final String EMPTY_FIELD = "Please, Enter the Container Number.";
	
	public static final String SERVICE_NOT_FOUND_ERROR = "We are unable to process request. Please try again later";
	
	public static final String USER_INFO_PREF = "userInfoStore";
	public static final String AGENT_ID = "retrievedAgentId";
	public static final String CARD_ID = "retrievedCardId";
	
	public static final String WEIGHMENT_WEIGHMENT = "WEIGHMENT";
	public static final String WEIGHMENT_WEIGH = "WEIGH";
	
	public static final String SCAN_SCAN_CT = "SCAN_CT";
	public static final String SCAN_SCAN = "SCAN";
	
	public static final String SEAL_VARIFICATION = "SEALVERIFY";
	
	public static final String FUMIGATION_FUMIGATE = "FUMIGATE";
	public static final String FUMIGATION_SEAL_BREAK = "SEAL_BREAK";
	
	public static final String SAMPLING_HOTWOK_EXM = "HOTWOK_EXM";
	public static final String SAMPLING_SAMPLE = "SAMPLE";
	
	public static final String IMP_ANF = "ANFEXAM";
	
	public static final String ON_CUST_FIVE = "5%";
	public static final String ON_CUST_HUND = "100%";
	
	public static final String KEY_CRYPT = "@#$%^&#5#@#*&$#@#$TY!@#$";
	public static final String VECTOR_IV = "DP_WORLD_VECTOR";
	
	public static String trimString(String s){
		return s.trim();
	}
	
	
	public static void quickToast(Context c, String s){
		Toast.makeText(c, s, Toast.LENGTH_SHORT).show();
	}
	
	public static void longToast(Context c, String s){
		Toast.makeText(c, s, Toast.LENGTH_LONG).show();
	}
	
	public void hideSoftKeys(Context c){
	}
	
    public static boolean isOn(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        return (networkInfo != null && networkInfo.isConnected());
    }

    public static boolean isWIFIOn(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        return (networkInfo != null && networkInfo.isConnected());
    }

    public static boolean isMobileOn(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        return (networkInfo != null && networkInfo.isConnected());
    }
    
	public static String crypt(String plain_Text){
		String putOut = "";		
		try {
			Crisptographer crypt_Instance = new Crisptographer();
			String key = Crisptographer.SHA256(DPHelper.KEY_CRYPT, 32);
			putOut = crypt_Instance.encrypt(plain_Text, key, DPHelper.VECTOR_IV);
		} catch (Exception e) {}
		return putOut;
	}    
    
	public static String convertStringToDate(Date indate){
		   String dateString = null;
		   SimpleDateFormat sdfr = new SimpleDateFormat("yyyy-mm-dd");
		   try{
			dateString = sdfr.format( indate );
		   }catch (Exception ex ){
			System.out.println(ex);
		   }
		   return dateString;
		}    
  
	@SuppressLint("NewApi")
	public static TextView drawTableLeftColumn(Context c, String value){
		ShapeDrawable border = new ShapeDrawable(new RectShape());
		border.getPaint().setStyle(Style.STROKE);
		border.getPaint().setStyle(Style.FILL);
		//border.getPaint().setColor(c.getResources().getColor(R.color.dark_blue));
		
		TableRow.LayoutParams lp = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.5f);
		
		TextView colEqnrb = new TextView(c);
		colEqnrb.setPadding(16, 16, 16, 16);
		colEqnrb.setTextSize(12);
		//colEqnrb.setBackground(border);
		colEqnrb.setLayoutParams(lp);
		colEqnrb.setTextColor(c.getResources().getColor(R.color.white));
		colEqnrb.setText(value);
		return colEqnrb;
	}    
    
	@SuppressLint("NewApi")
	public static TextView drawTableRightColumn(Context c, String value){
		ShapeDrawable border = new ShapeDrawable(new RectShape());
		border.getPaint().setStyle(Style.STROKE);
		border.getPaint().setStyle(Style.FILL);
		//border.getPaint().setColor(c.getResources().getColor(R.color.light_blue));
		
		TableRow.LayoutParams lp = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.5f);
		
		TextView colEqnrbVal = new TextView(c);
		colEqnrbVal = new TextView(c);
		colEqnrbVal.setPadding(16, 16, 16, 16);
		colEqnrbVal.setTextSize(12);
		colEqnrbVal.setAllCaps(true);
		//colEqnrbVal.setBackground(border);
		colEqnrbVal.setGravity(Gravity.RIGHT);
		colEqnrbVal.setLayoutParams(lp);
		colEqnrbVal.setTextColor(c.getResources().getColor(R.color.white));
		colEqnrbVal.setText(value);
		return colEqnrbVal;
	}
	
	public static void waitSpinInit(){
		
	}
	
}
