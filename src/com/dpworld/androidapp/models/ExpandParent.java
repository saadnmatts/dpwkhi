package com.dpworld.androidapp.models;

import java.util.ArrayList;

public class ExpandParent {
	
	private String parentName;
	private ArrayList<ExpandChild> childList = new ArrayList<ExpandChild>();
	
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public ArrayList<ExpandChild> getChildList() {
		return childList;
	}
	public void setChildList(ArrayList<ExpandChild> childList) {
		this.childList = childList;
	}
	
	
}
