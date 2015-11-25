package com.dpworld.androidapp.models;

public class ModelVesselSchedule {
	
	private String NAME,IN_VOY_NBR,VIR_NO,ETA,ETD,ATA,ATD,CUTOFF,SERVICE;
	
	public ModelVesselSchedule(String NAME,String IN_VOY_NBR,String VIR_NO,String ETA,String ETD,String ATA,String ATD,String CUTOFF) {
		this.NAME = NAME;
		this.IN_VOY_NBR = IN_VOY_NBR;
		this.VIR_NO = VIR_NO;
		this.ETA = ETA;
		this.ETD = ETD;
		this.ATA = ATA;
		this.ATD = ATD;
		this.CUTOFF = CUTOFF;
	}
	
	public ModelVesselSchedule() {
	}

	public String getNAME() {
		return NAME;
	}

	public String getIN_VOY_NBR() {
		return IN_VOY_NBR;
	}

	public String getVIR_NO() {
		return VIR_NO;
	}

	public String getETA() {
		return ETA;
	}

	public String getETD() {
		return ETD;
	}

	public String getATA() {
		return ATA;
	}

	public String getATD() {
		return ATD;
	}

	public String getCUTOFF() {
		return CUTOFF;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public void setIN_VOY_NBR(String iN_VOY_NBR) {
		IN_VOY_NBR = iN_VOY_NBR;
	}

	public void setVIR_NO(String vIR_NO) {
		VIR_NO = vIR_NO;
	}

	public void setETA(String eTA) {
		ETA = eTA;
	}

	public void setETD(String eTD) {
		ETD = eTD;
	}

	public void setATA(String aTA) {
		ATA = aTA;
	}

	public void setATD(String aTD) {
		ATD = aTD;
	}

	public void setCUTOFF(String cUTOFF) {
		CUTOFF = cUTOFF;
	}

	public String getSERVICE() {
		return SERVICE;
	}

	public void setSERVICE(String sERVICE) {
		SERVICE = sERVICE;
	}
	
	
}
