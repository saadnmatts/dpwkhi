package com.dpworld.androidapp.showparsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dpworld.androidapp.helpers.DPHelper;
import com.dpworld.androidapp.models.ModelContainerInquiry;
import com.dpworld.androidapp.models.ModelReleaseStatus;
import com.google.gson.Gson;

import android.content.Context;
import android.util.Log;

public class ShowParseContainerInquiry {
	
	Context c;
	String jsonResult;
	
	public ShowParseContainerInquiry(Context c, String jsonResult){
		this.c = c;
		this.jsonResult = jsonResult;  
	}
	
	public List<ModelContainerInquiry> onDataLoadedContainerInquiry(){
		ArrayList<ModelContainerInquiry> arrayList = new ArrayList<ModelContainerInquiry>();
		ModelContainerInquiry obj;
		ModelContainerInquiry[] items = (new Gson()).fromJson(jsonResult, ModelContainerInquiry[].class);
		if(items.length != 0){			
			for (ModelContainerInquiry item : items) {
				obj = new ModelContainerInquiry();
				obj.setFIELD_NAME(item.getFIELD_NAME());
				obj.setFIELD_VALUE(item.getFIELD_VALUE());				
				arrayList.add(obj);
			}			
		}else{
			DPHelper.quickToast(c, "No Data Found.");
			obj = null;			
		}
		return arrayList;		
	}
	
}
