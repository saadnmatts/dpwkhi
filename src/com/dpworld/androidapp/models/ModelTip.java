package com.dpworld.androidapp.models;

public class ModelTip {
	private String GKEY, CTR_NBR, SC_AGENT, UPDATED_TRUCK_NO = "";
//	private double GKEY;
	
	public String getCTR_NBR() {
		return CTR_NBR;
	}

	public void setCTR_NBR(String cTR_NBR) {
		CTR_NBR = cTR_NBR;
	}

	public String getSC_AGENT() {
		return SC_AGENT;
	}

	public void setSC_AGENT(String sC_AGENT) {
		SC_AGENT = sC_AGENT;
	}

	public String getGKEY() {
		return GKEY;
	}

	public void setGKEY(String gKEY) {
		GKEY = gKEY;
	}

	public String getUPDATED_TRUCK_NO() {
		return UPDATED_TRUCK_NO;
	}

	public void setUPDATED_TRUCK_NO(String uPDATED_TRUCK_NO) {
		UPDATED_TRUCK_NO = uPDATED_TRUCK_NO;
	}
}
