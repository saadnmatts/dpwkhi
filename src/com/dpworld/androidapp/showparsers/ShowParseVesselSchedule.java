package com.dpworld.androidapp.showparsers;

import java.util.ArrayList;
import java.util.List;

import com.dpworld.androidapp.helpers.DPHelper;
import com.dpworld.androidapp.models.ModelVesselSchedule;
import com.google.gson.Gson;

import android.content.Context;
import android.util.Log;

public class ShowParseVesselSchedule {
	
	Context c;
	String jsonResult;
	
	public ShowParseVesselSchedule(Context c, String jsonResult) {
		this.c = c;
		this.jsonResult = jsonResult;
	}
	
	public List<ModelVesselSchedule> onDataLoadedVesselSchedule(){
		ArrayList<ModelVesselSchedule> arrayList = new ArrayList<ModelVesselSchedule>();
		ModelVesselSchedule obj;
		ModelVesselSchedule[] items = (new Gson()).fromJson(jsonResult, ModelVesselSchedule[].class);
		if(items.length != 0){
			for(ModelVesselSchedule item : items){
				obj = new ModelVesselSchedule();
				obj.setNAME(item.getNAME());
				obj.setIN_VOY_NBR(item.getIN_VOY_NBR());
				obj.setVIR_NO(item.getVIR_NO());
				obj.setETA(item.getETA());
				obj.setETD(item.getETD());
				obj.setATA(item.getATA());
				obj.setATD(item.getATD());
				obj.setCUTOFF(item.getCUTOFF());
				obj.setSERVICE(item.getSERVICE());
				arrayList.add(obj);
			}
		}else{
			DPHelper.quickToast(c, "No Data Found.");
			obj = null;				
		}
		return arrayList;
	}
	
}
