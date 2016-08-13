package com.example.web.model.request;

import org.springframework.web.multipart.MultipartFile;


public class UserRegistration {
	
	private String carMakeModel;

	private String carRegNum;

	private String company;

	private String defaultHomeAddress;

	private String defaultHomeLandmark;

	private String defaultOfficeAddress;

	private String defaultOfficeLandmark;

	private String fullname;

	private String gender;

	private String mobile;

	private Integer numOfSeatsShare;

	private String otp;

	private String password;

	private String sourceToggle;

	private Integer termCondition;

	private String email;
	
	private String carImagePath;
	
	private String userImagePath;
	
	private String deviceId;
	
	private String sourceLandMarkLatLong;
	
	private String destinationLandMarkLatLong;
	
	private String userType;
	
	private MultipartFile carImage;
	private MultipartFile userImage;
	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getCarMakeModel() {
		return carMakeModel;
	}
	public void setCarMakeModel(String carMakeModel) {
		this.carMakeModel = carMakeModel;
	}
	public String getCarRegNum() {
		return carRegNum;
	}
	public void setCarRegNum(String carRegNum) {
		this.carRegNum = carRegNum;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDefaultHomeAddress() {
		return defaultHomeAddress;
	}
	public void setDefaultHomeAddress(String defaultHomeAddress) {
		this.defaultHomeAddress = defaultHomeAddress;
	}
	public String getDefaultHomeLandmark() {
		return defaultHomeLandmark;
	}
	public void setDefaultHomeLandmark(String defaultHomeLandmark) {
		this.defaultHomeLandmark = defaultHomeLandmark;
	}
	public String getDefaultOfficeAddress() {
		return defaultOfficeAddress;
	}
	public void setDefaultOfficeAddress(String defaultOfficeAddress) {
		this.defaultOfficeAddress = defaultOfficeAddress;
	}
	public String getDefaultOfficeLandmark() {
		return defaultOfficeLandmark;
	}
	public void setDefaultOfficeLandmark(String defaultOfficeLandmark) {
		this.defaultOfficeLandmark = defaultOfficeLandmark;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getNumOfSeatsShare() {
		return numOfSeatsShare;
	}
	public void setNumOfSeatsShare(Integer numOfSeatsShare) {
		this.numOfSeatsShare = numOfSeatsShare;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSourceToggle() {
		return sourceToggle;
	}
	public void setSourceToggle(String sourceToggle) {
		this.sourceToggle = sourceToggle;
	}
	public Integer getTermCondition() {
		return termCondition;
	}
	public void setTermCondition(Integer termCondition) {
		this.termCondition = termCondition;
	}
	public String getCarImagePath() {
		return carImagePath;
	}
	public void setCarImagePath(String carImagePath) {
		this.carImagePath = carImagePath;
	}
	public String getUserImagePath() {
		return userImagePath;
	}
	public void setUserImagePath(String userImagePath) {
		this.userImagePath = userImagePath;
	}
	public MultipartFile getCarImage() {
		return carImage;
	}
	public void setCarImage(MultipartFile carImage) {
		this.carImage = carImage;
	}
	public MultipartFile getUserImage() {
		return userImage;
	}
	public void setUserImage(MultipartFile userImage) {
		this.userImage = userImage;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getSourceLandMarkLatLong() {
		return sourceLandMarkLatLong;
	}
	public void setSourceLandMarkLatLong(String sourceLandMarkLatLong) {
		this.sourceLandMarkLatLong = sourceLandMarkLatLong;
	}
	public String getDestinationLandMarkLatLong() {
		return destinationLandMarkLatLong;
	}
	public void setDestinationLandMarkLatLong(String destinationLandMarkLatLong) {
		this.destinationLandMarkLatLong = destinationLandMarkLatLong;
	}
	
	
}
