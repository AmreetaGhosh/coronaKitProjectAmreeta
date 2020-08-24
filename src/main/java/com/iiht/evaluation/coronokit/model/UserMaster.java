package com.iiht.evaluation.coronokit.model;

public class UserMaster {
	
	private int id;
	private String visitorName;
	private String visitorEmail;
	private String visitorContactNumber;
	private String visitorDeliveryAddress;
	

	
	
	public UserMaster(int id, String visitorName, String visitorEmail, String visitorContactNumber,
			String visitorDeliveryAddress) {
		
		this.id = id;
		this.visitorName = visitorName;
		this.visitorEmail = visitorEmail;
		this.visitorContactNumber = visitorContactNumber;
		this.visitorDeliveryAddress = visitorDeliveryAddress;
	}
	
	public UserMaster(String visitorName, String visitorEmail, String visitorContactNumber,
			String visitorDeliveryAddress) {
	
		this.visitorName = visitorName;
		this.visitorEmail = visitorEmail;
		this.visitorContactNumber = visitorContactNumber;
		this.visitorDeliveryAddress = visitorDeliveryAddress;
	}
	
	public UserMaster(String visitorName, String visitorEmail, String visitorContactNumber) {
	
		this.visitorName = visitorName;
		this.visitorEmail = visitorEmail;
		this.visitorContactNumber = visitorContactNumber;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVisitorName() {
		return visitorName;
	}
	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}
	public String getVisitorEmail() {
		return visitorEmail;
	}
	public void setVisitorEmail(String visitorEmail) {
		this.visitorEmail = visitorEmail;
	}
	public String getVisitorContactNumber() {
		return visitorContactNumber;
	}
	public void setVisitorContactNumber(String visitorContactNumber) {
		this.visitorContactNumber = visitorContactNumber;
	}
	public String getVisitorDeliveryAddress() {
		return visitorDeliveryAddress;
	}
	public void setVisitorDeliveryAddress(String visitorDeliveryAddress) {
		this.visitorDeliveryAddress = visitorDeliveryAddress;
	}

	

}
