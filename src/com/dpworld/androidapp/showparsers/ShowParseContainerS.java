package com.dpworld.androidapp.showparsers;

import com.dpworld.androidapp.helpers.DPHelper;
import com.dpworld.androidapp.models.ModelReleaseStatus;
import com.google.gson.Gson;

import android.content.Context;

public class ShowParseContainerS {
	
	Context c;
	String jsonResult;
	
	public ShowParseContainerS(Context c, String jsonResult) {
		this.c = c;
		this.jsonResult = jsonResult;
	}
	
	public ModelReleaseStatus onDataLoadedContainerStatus(){
		ModelReleaseStatus obj;
		ModelReleaseStatus[] items = (new Gson()).fromJson(jsonResult, ModelReleaseStatus[].class);
		if(items.length != 0){
			obj = new ModelReleaseStatus();
			for (ModelReleaseStatus item : items) {
				obj.setEQ_NBR(item.getEQ_NBR());
				obj.setGROUP_ID(item.getGROUP_ID());
				obj.setLOCATION(item.getLOCATION());
				obj.setWEIGHT(item.getWEIGHT());
				obj.setSCAN_STATUS(item.getSCAN_STATUS());
				obj.setANF_STATUS(item.getANF_STATUS());
			}
		}else{
			DPHelper.quickToast(c, "No Data Found.");
			obj = null;			
		}
		return obj;
	}	
	
}
