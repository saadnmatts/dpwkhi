package com.dpworld.androidapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;

public class SplashScreen extends Activity {
	
    private static final int STOPSPLASH = 0;

    private static final long SPLASHTIME = 2500;

    private Handler splashHandler = new Handler() {
	    @Override
	    public void handleMessage(Message msg) {
		    switch (msg.what) {
			    case STOPSPLASH:
			    Intent intent = new Intent(SplashScreen.this, MainMenu.class);
			    startActivity(intent);
			    break;
		    }
		    super.handleMessage(msg);
	    }
    };	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash_screen);
	    Message msg = new Message();
	    msg.what = STOPSPLASH;
	    splashHandler.sendMessageDelayed(msg, SPLASHTIME);		
	}
	
}
