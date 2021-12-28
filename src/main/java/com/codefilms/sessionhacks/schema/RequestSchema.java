package com.codefilms.sessionhacks.schema;

public class RequestSchema {
	
	String phoneNumber;
	Double amount;
	
	public RequestSchema() {
		
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "RequestSchema [phoneNumber=" + phoneNumber + ", amount=" + amount + "]";
	}
	
	
	

}
