package com.mahendra;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Account {

	private String accHolderName;
	private String accNumber;
	private Date openingDate;
	private String type;
	public String getAccHolderName() {
		return accHolderName;
	}
	public void setAccHolderName(String accHolderName) {
		this.accHolderName = accHolderName;
	}
	public String getAccNumber() {
		return accNumber;
	}
	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}
	public Date getOpeningDate() {
		return openingDate;
	}
	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
