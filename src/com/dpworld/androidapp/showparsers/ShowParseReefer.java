package com.dpworld.androidapp.showparsers;

import android.content.Context;
import android.util.Log;
import android.widget.TableLayout;

import com.dpworld.androidapp.helpers.DPHelper;
import com.dpworld.androidapp.models.ModelReeferInquiry;
import com.google.gson.Gson;

public class ShowParseReefer {
	
	Context c;
	String jsonResult;
	
	public ShowParseReefer(Context c, String jsonResult){
		this.c = c;
		this.jsonResult = jsonResult;		
	}
	
	public ModelReeferInquiry onDataLoadedReeferInquiry(){
		ModelReeferInquiry obj;
		ModelReeferInquiry[] items = (new Gson()).fromJson(jsonResult, ModelReeferInquiry[].class);
		if(items.length != 0){
			obj = new ModelReeferInquiry();
			for (ModelReeferInquiry item : items) {
				obj.setNBR(item.getNBR());
				obj.setSET_TEMP(item.getSET_TEMP());
				obj.setCURRENT_TEMP(item.getCURRENT_TEMP());
				obj.setVENT(item.getVENT());
				obj.setHUMIDITY_REQUIRED(item.getHUMIDITY_REQUIRED());
				obj.setCOM_DES(item.getCOM_DES());
				obj.setCATEGORY(item.getCATEGORY());
			}
		}else{
			DPHelper.quickToast(c, "No Data Found.");
			obj = null;			
		}
		return obj;		
	}

}
