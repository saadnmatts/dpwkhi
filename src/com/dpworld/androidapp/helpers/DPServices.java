package com.dpworld.androidapp.helpers;

import java.sql.Date;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.text.format.DateFormat;
import android.util.Log;

public class DPServices {
	
	public static final String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";
	public static final String SOAP_ADDRESS = "http://lfs.qict.com.pk/vbs/service.asmx";
	
	String operationName = "";
	
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	public String getSoapAction(){
			String toReturn = WSDL_TARGET_NAMESPACE + operationName;
		return toReturn;
	}
	
	public static String containerStatus(String containerNo, String userid, String pass){
		final String SOAP_ACTION = "http://tempuri.org/Container_Status";
		final String OPERATION_NAME = "Container_Status";
	
		SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
		PropertyInfo pi;
		
		pi=new PropertyInfo();
		pi.setName("Container_No");
	    pi.setValue(containerNo);
	    pi.setType(String.class);
	    request.addProperty(pi);
	    pi=new PropertyInfo();
	    pi.setName("UserID");
	    pi.setValue(userid);
	    pi.setType(String.class);
	    request.addProperty(pi);
	    pi=new PropertyInfo();
	    pi.setName("Password");
	    pi.setValue(pass);
	    pi.setType(String.class);
	    request.addProperty(pi);	    
	
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
		SoapEnvelope.VER11);
		envelope.dotNet = true;
	
		envelope.setOutputSoapObject(request);
	
		HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
		Object response=null;
		try{
			httpTransport.call(SOAP_ACTION, envelope);
			response = envelope.getResponse();
		}
		catch (Exception exception){
			response="";
		}
		return response.toString();
	}
	
	public static String holdStatus(String containerNo){
		final String OPERATION_NAME = "Hold_Status";
		final String SOAP_ACTION = "http://tempuri.org/Hold_Status";
		SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
		PropertyInfo pi;
		
		pi=new PropertyInfo();
		pi.setName("Container_No");
	    pi.setValue(containerNo);
	    pi.setType(String.class);
	    request.addProperty(pi);
	    pi=new PropertyInfo();
	    pi.setName("UserID");
	    pi.setValue(DPHelper.MAIN_USERNAME);
	    pi.setType(String.class);
	    request.addProperty(pi);
	    pi=new PropertyInfo();
	    pi.setName("Password");
	    pi.setValue(DPHelper.MAIN_PASSWORD);
	    pi.setType(String.class);
	    request.addProperty(pi);	    
	
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
		SoapEnvelope.VER11);
		envelope.dotNet = true;
	
		envelope.setOutputSoapObject(request);
	
		HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
		Object response=null;
		try{
			httpTransport.call(SOAP_ACTION, envelope);
			response = envelope.getResponse();
		}
		catch (Exception exception){
			response="";
		}
		return response.toString();		
	}
	
	public static String containerInquiry(String containerNo, String userid, String pass){
		final String SOAP_ACTION = "http://tempuri.org/Container_Inquiry";
		final String OPERATION_NAME = "Container_Inquiry";
	
		SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
		PropertyInfo pi;
		
		pi=new PropertyInfo();
		pi.setName("ContainerNo");
	    pi.setValue(containerNo);
	    pi.setType(String.class);
	    request.addProperty(pi);
	    pi=new PropertyInfo();
	    pi.setName("UserID");
	    pi.setValue(userid);
	    pi.setType(String.class);
	    request.addProperty(pi);
	    pi=new PropertyInfo();
	    pi.setName("Password");
	    pi.setValue(pass);
	    pi.setType(String.class);
	    request.addProperty(pi);	    
	
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
		SoapEnvelope.VER11);
		envelope.dotNet = true;
	
		envelope.setOutputSoapObject(request);
	
		HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
		Object response=null;
		try{
			httpTransport.call(SOAP_ACTION, envelope);
			response = envelope.getResponse();
		}
		catch (Exception exception){
			response="";
		}
		return response.toString();		
	}	
	
	public static String reeferInquiry(String containerNo, String userid, String pass){
		final String SOAP_ACTION = "http://tempuri.org/Reefer_Inquiry";
		final String OPERATION_NAME = "Reefer_Inquiry";
	
		SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
		PropertyInfo pi;
		
		pi=new PropertyInfo();
		pi.setName("ContainerNo");
	    pi.setValue(containerNo);
	    pi.setType(String.class);
	    request.addProperty(pi);
	    pi=new PropertyInfo();
	    pi.setName("UserID");
	    pi.setValue(userid);
	    pi.setType(String.class);
	    request.addProperty(pi);
	    pi=new PropertyInfo();
	    pi.setName("Password");
	    pi.setValue(pass);
	    pi.setType(String.class);
	    request.addProperty(pi);	    
	
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
		SoapEnvelope.VER11);
		envelope.dotNet = true;
	
		envelope.setOutputSoapObject(request);
	
		HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
		Object response=null;
		try{
			httpTransport.call(SOAP_ACTION, envelope);
			response = envelope.getResponse();
		}
		catch (Exception exception){
			response="";
		}
		return response.toString();					
	}
	
	public static String vesselSchedule(String userid, String pass){
		final String OPERATION = "Vessel_Schedule";
		final String SOAP_ACTION = "http://tempuri.org/Vessel_Schedule";
		SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION);
		PropertyInfo pi;

	    pi=new PropertyInfo();
	    pi.setName("UserID");
	    pi.setValue(userid);
	    pi.setType(String.class);
	    request.addProperty(pi);
	    pi=new PropertyInfo();
	    pi.setName("Password");
	    pi.setValue(pass);
	    pi.setType(String.class);
	    request.addProperty(pi);	    
	
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
		SoapEnvelope.VER11);
		envelope.dotNet = true;
	
		envelope.setOutputSoapObject(request);
	
		HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
		Object response=null;
		try{
			httpTransport.call(SOAP_ACTION, envelope);
			response = envelope.getResponse();
		}
		catch (Exception exception){
			response="";
		}
		return response.toString();		
	}
	
	public static String invoiceInquiry(String blno, String dateTime, String userid, String pass){
		final String OPERATION = "Invoice_Enquiry";
		final String SOAP_ACTION = "http://tempuri.org/Invoice_Enquiry";
		SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION);
		PropertyInfo pi;

		pi = new PropertyInfo();
		pi.setName("bl_no");
		pi.setValue(blno);
		pi.setType(String.class);
		request.addProperty(pi);
		
		pi = new PropertyInfo();
		pi.setName("validity");
		pi.setValue(dateTime);
		pi.setType(String.class);
		request.addProperty(pi);
		
	    pi=new PropertyInfo();
	    pi.setName("UserID");
	    pi.setValue(userid);
	    pi.setType(String.class);
	    request.addProperty(pi);
	    pi=new PropertyInfo();
	    pi.setName("Password");
	    pi.setValue(pass);
	    pi.setType(String.class);
	    request.addProperty(pi);	    
	
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
		SoapEnvelope.VER11);
		envelope.dotNet = true;
	
		envelope.setOutputSoapObject(request);
	
		HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
		Object response=null;
		try{
			httpTransport.call(SOAP_ACTION, envelope);
			response = envelope.getResponse();
		}
		catch (Exception exception){
			response = "";
		}
		return response.toString();		
	}
	
	public static String authenticatePassword(String agentuserid, String agentPassword){
		final String OPERATION = "Authencaite_Password";
		final String SOAP_ACTION = "http://tempuri.org/Authencaite_Password";
		SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION);
		PropertyInfo pi;

		pi = new PropertyInfo();
		pi.setName("Agent_User_ID");
		pi.setValue(agentuserid);
		pi.setType(String.class);
		request.addProperty(pi);
		
		pi = new PropertyInfo();
		pi.setName("Agent_Password");
		pi.setValue(agentPassword);
		pi.setType(String.class);
		request.addProperty(pi);
		
	    pi=new PropertyInfo();
	    pi.setName("UserID");
	    pi.setValue(DPHelper.MAIN_USERNAME);
	    pi.setType(String.class);
	    request.addProperty(pi);
	    
	    pi=new PropertyInfo();
	    pi.setName("Password");
	    pi.setValue(DPHelper.MAIN_PASSWORD);
	    pi.setType(String.class);
	    request.addProperty(pi);
	    
	    Log.i(DPHelper.LOGTAG,"I am request: "+ request.toString());

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
		SoapEnvelope.VER11);
		envelope.dotNet = true;
	
		envelope.setOutputSoapObject(request);
	
		HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
		Object response=null;
		try{
			httpTransport.call(SOAP_ACTION, envelope);
			response = envelope.getResponse();
		}
		catch (Exception exception){
			response="";
		}
		return response.toString();		
	}
	
	public static String serviceRequest(String serviceId, String containerNo, String agentId, String cardId){
		final String OPERATION = "Service_Request";
		final String SOAP_ACTION = "http://tempuri.org/Service_Request";
		SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION);
		PropertyInfo pi;

		pi = new PropertyInfo();
		pi.setName("ServiceID");
		pi.setValue(serviceId);
		pi.setType(String.class);
		request.addProperty(pi);
		
		pi = new PropertyInfo();
		pi.setName("ContainerNo");
		pi.setValue(containerNo);
		pi.setType(String.class);
		request.addProperty(pi);
		
	    pi=new PropertyInfo();
	    pi.setName("AgentID");
	    pi.setValue(agentId);
	    pi.setType(String.class);
	    request.addProperty(pi);
	    
	    pi=new PropertyInfo();
	    pi.setName("CardID");
	    pi.setValue(cardId);
	    pi.setType(String.class);
	    request.addProperty(pi);
	    
	    pi=new PropertyInfo();
	    pi.setName("UserID");
	    pi.setValue(DPHelper.MAIN_USERNAME);
	    pi.setType(String.class);
	    request.addProperty(pi);
	    
	    pi=new PropertyInfo();
	    pi.setName("Password");
	    pi.setValue(DPHelper.MAIN_PASSWORD);
	    pi.setType(String.class);
	    request.addProperty(pi);	    

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
		SoapEnvelope.VER11);
		envelope.dotNet = true;
	
		envelope.setOutputSoapObject(request);
	
		HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
		Object response=null;
		try{
			httpTransport.call(SOAP_ACTION, envelope);
			response = envelope.getResponse();
		}
		catch (Exception exception){
			response="";
		}
		return response.toString();		
	}
	
	public static String tipList(String agentid){
		final String OPERATION = "TIP_List";
		final String SOAP_ACTION = "http://tempuri.org/TIP_List";
		SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION);
		PropertyInfo pi;
		
	    pi=new PropertyInfo();
	    pi.setName("Agent_ID");
	    pi.setValue(agentid);
	    pi.setType(String.class);
	    request.addProperty(pi);		

	    pi=new PropertyInfo();
	    pi.setName("UserID");
	    pi.setValue(DPHelper.MAIN_USERNAME);
	    pi.setType(String.class);
	    request.addProperty(pi);
	    
	    pi=new PropertyInfo();
	    pi.setName("Password");
	    pi.setValue(DPHelper.MAIN_PASSWORD);
	    pi.setType(String.class);
	    request.addProperty(pi);
	    
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
		SoapEnvelope.VER11);
		envelope.dotNet = true;
	
		envelope.setOutputSoapObject(request);
	
		HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
		Object response=null;
		try{
			httpTransport.call(SOAP_ACTION, envelope);
			response = envelope.getResponse();
		}
		catch (Exception exception){
			response="";
		}
		return response.toString();		    

	}
	
	public static String updateTruck(String gKey, String containerNo, String truckNo){
		final String OPERATION = "update_truck_no";
		final String SOAP_ACTION = "http://tempuri.org/update_truck_no";
		SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION);
		PropertyInfo pi;
		
	    pi=new PropertyInfo();
	    pi.setName("gkey");
	    pi.setValue(gKey);
	    pi.setType(Double.class);
	    request.addProperty(pi);		

	    pi=new PropertyInfo();
	    pi.setName("ContainerNo");
	    pi.setValue(containerNo);
	    pi.setType(String.class);
	    request.addProperty(pi);
	    
	    pi=new PropertyInfo();
	    pi.setName("TruckNo");
	    pi.setValue(truckNo);
	    pi.setType(String.class);
	    request.addProperty(pi);
	    
	    pi=new PropertyInfo();
	    pi.setName("UserID");
	    pi.setValue(DPHelper.MAIN_USERNAME);
	    pi.setType(String.class);
	    request.addProperty(pi);
	    
	    pi=new PropertyInfo();
	    pi.setName("Password");
	    pi.setValue(DPHelper.MAIN_PASSWORD);
	    pi.setType(String.class);
	    request.addProperty(pi);
	    
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
		SoapEnvelope.VER11);
		envelope.dotNet = true;
	
		envelope.setOutputSoapObject(request);
	
		HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
		Object response=null;
		try{
			httpTransport.call(SOAP_ACTION, envelope);
			response = envelope.getResponse();
		}
		catch (Exception exception){
			response="";
		}
		return response.toString();		    
	}	
	
}
