package com.dpworld.androidapp.showparsers;

import java.util.ArrayList;
import java.util.List;

import com.dpworld.androidapp.helpers.DPHelper;
import com.dpworld.androidapp.models.ModelHoldStatus;
import com.google.gson.Gson;

import android.content.Context;

public class ShowParseHold {
	
	Context c;
	String jsonResult;
	
	public ShowParseHold(Context c, String jsonResult){
		this.c = c;
		this.jsonResult = jsonResult;
	}
	
	public List<ModelHoldStatus> onDataLoadedContainerInquiry(){
		ArrayList<ModelHoldStatus> arrayList = new ArrayList<ModelHoldStatus>();
		ModelHoldStatus obj;
		ModelHoldStatus[] items = (new Gson()).fromJson(jsonResult, ModelHoldStatus[].class);
		if(items.length != 0){			
			for (ModelHoldStatus item : items) {
				obj = new ModelHoldStatus();
				obj.setEQ_NBR(item.getEQ_NBR());
				obj.setHELD_REASON(item.getHELD_REASON());
				obj.setHOLD_DATE(item.getHOLD_DATE());
				obj.setRELEASE_DATE(item.getRELEASE_DATE());
				obj.setDOCUMENT_REQUIRED(item.getDOCUMENT_REQUIRED());
				arrayList.add(obj);
			}			
		}else{
			DPHelper.quickToast(c, "No Data Found.");
			obj = null;			
		}
		return arrayList;		
	}	

}
