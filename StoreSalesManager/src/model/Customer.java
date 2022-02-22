package model;

import observer.Message;
import observer.Receiver;
import observer.Sender;

public class Customer implements Receiver{

	private String name;
	private String phoneNumber;
	private boolean notification=false;
	

	public Customer(String name, String phoneNumber, boolean notification) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.notification = notification;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isNotification() {
		return notification;
	}

	public void setNotification(boolean notification) {
		this.notification = notification;
	}
	@Override
	public String toString() {
		return name+", "+"Phone: "+phoneNumber;
	}

	@Override
	public String receiveMSG(Sender s, Message msg) {
		StringBuffer sb = new StringBuffer();
		sb.append("New Message Has Arrived ---> From: "+this.phoneNumber+" || "+this.name+"\n");
		return sb.toString();
	}
}
