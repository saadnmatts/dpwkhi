package com.dpworld.androidapp.helpers;

import java.util.ArrayList;

import com.dpworld.androidapp.models.ModelTip;

public class ContainerList {
	private static ContainerList Instance;
	private ArrayList<ModelTip> containersList;
	
	public ContainerList(){
		this.containersList = new ArrayList<ModelTip>();
	}
	
	public static ContainerList getInstance(){
		if(Instance == null)
			Instance = new ContainerList();
		return Instance;
	}
	
	public void set(ArrayList<ModelTip> containersList){
		this.containersList = containersList;
	}
	
	public ArrayList<ModelTip> get(){
		return this.containersList;
	}	

	public void addTruckNumber(String truckNum, int pos){
		this.containersList.get(pos).setUPDATED_TRUCK_NO(truckNum);
	}
	
//	public ModelListItem getItem(int index){
//		return this.items.get(index);
//	}
//	
//	public void delete(int index){
//		this.items.remove(index);
//	}
	
	public int size(){
		return this.containersList.size();
	}
	
	public void clear(){
		this.containersList.clear();
	}	
	
}
