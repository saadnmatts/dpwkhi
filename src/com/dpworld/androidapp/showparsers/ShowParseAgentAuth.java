package com.dpworld.androidapp.showparsers;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

import com.dpworld.androidapp.helpers.DPHelper;

import android.content.Context;
import android.util.Log;

public class ShowParseAgentAuth {
	
	Context c;
	String jsonResult;
	
	public ShowParseAgentAuth(Context c, String jsonResult){
		this.c = c;
		this.jsonResult = jsonResult;
	}
	
	public ArrayList<String> onDataLoadedAuthPass(){
		ArrayList<String> empty = new ArrayList<String>();
		ArrayList<String> results = new ArrayList<String>();
		String agent,card;
		try {
			JSONArray jsonArray = new JSONArray(jsonResult);
				 JSONObject jsonObject = jsonArray.getJSONObject(0);
				  agent = jsonObject.optString("SC_AGENT").toString();
				  card = jsonObject.optString("ID").toString();
				  results.add(agent);
				  results.add(card);
			 return results;
		} catch (Exception e) {
			return empty;
		}
	}
	
}
