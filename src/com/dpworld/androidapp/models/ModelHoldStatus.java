package com.dpworld.androidapp.models;

public class ModelHoldStatus {
	
	private String EQ_NBR, HELD_REASON, HOLD_DATE, RELEASED_DATE, DOCUMENT_REQUIRED;

	public String getEQ_NBR() {
		return EQ_NBR;
	}

	public String getHELD_REASON() {
		return HELD_REASON;
	}

	public String getHOLD_DATE() {
		return HOLD_DATE;
	}

	public String getRELEASE_DATE() {
		return RELEASED_DATE;
	}

	public String getDOCUMENT_REQUIRED() {
		return DOCUMENT_REQUIRED;
	}

	public void setEQ_NBR(String eQ_NBR) {
		EQ_NBR = eQ_NBR;
	}

	public void setHELD_REASON(String hELD_REASON) {
		HELD_REASON = hELD_REASON;
	}

	public void setHOLD_DATE(String hOLD_DATE) {
		HOLD_DATE = hOLD_DATE;
	}

	public void setRELEASE_DATE(String rELEASE_DATE) {
		RELEASED_DATE = rELEASE_DATE;
	}

	public void setDOCUMENT_REQUIRED(String dOCUMENT_REQUIRED) {
		DOCUMENT_REQUIRED = dOCUMENT_REQUIRED;
	}
	
}
