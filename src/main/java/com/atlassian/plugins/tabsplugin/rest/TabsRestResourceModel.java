package com.atlassian.plugins.tabsplugin.rest;

import javax.xml.bind.annotation.*;

//Result XML or JSON class for my entity row
@XmlRootElement(name = "row")
@XmlAccessorType(XmlAccessType.FIELD)
public class TabsRestResourceModel{
	
	//fields
	@XmlElement
	private int id;
	
	@XmlElement
	private String date;
	
	@XmlElement
	private String string; 
	
	//Constructors
	public TabsRestResourceModel(){
	}
	
	public TabsRestResourceModel(int id, String date, String string){
		this.id = id;
		this.string = string;
		this.date = date;
	}
	
	//Getters & Setters for fields
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getDate(){
		return date;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	
	public String getString(){
		return string;
	}
	
	public void setString(String string){
		this.string = string;
	}
}