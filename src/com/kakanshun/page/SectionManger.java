package com.kakanshun.page;

import java.io.Serializable;

public class SectionManger implements Serializable{
	private int sid;
	private String sName;
	private String mastername;
	private String Psection;
	
	public int getId() {
		return sid;
	}
	public void setId(int id) {
		this.sid = id;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getMastername() {
		return mastername;
	}
	public void setMastername(String mastername) {
		this.mastername = mastername;
	}
	public String getPsection() {
		return Psection;
	}
	public void setPsection(String psection) {
		Psection = psection;
	}
	
}
