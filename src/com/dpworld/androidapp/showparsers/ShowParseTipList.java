package com.dpworld.androidapp.showparsers;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import com.dpworld.androidapp.helpers.DPHelper;
import com.dpworld.androidapp.models.ModelTip;
import com.google.gson.Gson;

public class ShowParseTipList {
	Context c;
	String jsonResult;
	
	public ShowParseTipList(Context c, String jsonResult){
		this.c = c;
		this.jsonResult = jsonResult;  
	}
	
	public ArrayList<ModelTip> onDataLoadedTipList(){
		ArrayList<ModelTip> arrayList = new ArrayList<ModelTip>();
		ModelTip obj;
		ModelTip[] items = (new Gson()).fromJson(jsonResult, ModelTip[].class);
		if(items.length != 0){			
			for (ModelTip item : items) {
				obj = new ModelTip();
				obj.setGKEY(item.getGKEY());
				obj.setCTR_NBR(item.getCTR_NBR());
				obj.setSC_AGENT(item.getCTR_NBR());
				arrayList.add(obj);
			}			
		}else{
			DPHelper.quickToast(c, "No Data Found.");
			obj = null;			
		}
		return arrayList;		
	}
}
