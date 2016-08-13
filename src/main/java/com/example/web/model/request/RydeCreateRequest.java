package com.example.web.model.request;


public class RydeCreateRequest {
	
	private String destination;

	private double destinationLatitude;

	private double destinationLongitude;

	private String source;

	private double sourceLatitude;

	private double sourceLongitude;

	private String startTime;

	private int userId;
	
	private String userType;

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public double getDestinationLatitude() {
		return destinationLatitude;
	}

	public void setDestinationLatitude(double destinationLatitude) {
		this.destinationLatitude = destinationLatitude;
	}

	public double getDestinationLongitude() {
		return destinationLongitude;
	}

	public void setDestinationLongitude(double destinationLongitude) {
		this.destinationLongitude = destinationLongitude;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public double getSourceLatitude() {
		return sourceLatitude;
	}

	public void setSourceLatitude(double sourceLatitude) {
		this.sourceLatitude = sourceLatitude;
	}

	public double getSourceLongitude() {
		return sourceLongitude;
	}

	public void setSourceLongitude(double sourceLongitude) {
		this.sourceLongitude = sourceLongitude;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	
}
