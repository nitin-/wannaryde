package com.example.web.model.request;

public class ActionOnCard {
	int cardId;
	int userId;
	int rydeId;
	String userType;
	boolean accepted;

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRydeId() {
		return rydeId;
	}
	public void setRydeId(int rydeId) {
		this.rydeId = rydeId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public boolean getAccepted() {
		return accepted;
	}
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	
	
}
