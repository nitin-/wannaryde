package com.example.dal.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="car_image_path")
	private String carImagePath;

	@Column(name="car_make_model")
	private String carMakeModel;

	@Column(name="car_reg_num")
	private String carRegNum;

	private String company;

	@Column(name="default_home_address")
	private String defaultHomeAddress;

	@Column(name="default_home_landmark")
	private String defaultHomeLandmark;

	@Column(name="default_office_address")
	private String defaultOfficeAddress;

	@Column(name="default_office_landmark")
	private String defaultOfficeLandmark;
	
	private String email;

	private boolean enabled;
	
	@Column(name="otp_verified")
	private boolean otpVerified;

	private String fullname;

	private String gender;

	@Column(name="leaving_time")
	private Timestamp leavingTime;

	private String mobile;

	@Column(name="num_of_seats_share")
	private int numOfSeatsShare;

	private String otp;

	private String password;

	@Column(name="source_toggle")
	private String sourceToggle;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_time")
	private Date startTime;

	@Column(name="user_image_path")
	private String userImagePath;
	
	@JsonIgnore
	@OneToOne(mappedBy="user")
	private PushNotificationCrd pushNotificationCrd;
	
	@Column(name="source_landmark_lat_long")
	private String sourceLandMarkLatLong;
	
	@Column(name="destination_landmark_lat_long")
	private String destinationLandMarkLatLong;
	
	@Column(name="user_type")
	private String userType;
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCarImagePath() {
		return this.carImagePath;
	}

	public void setCarImagePath(String carImagePath) {
		this.carImagePath = carImagePath;
	}

	public String getCarMakeModel() {
		return this.carMakeModel;
	}

	public void setCarMakeModel(String carMakeModel) {
		this.carMakeModel = carMakeModel;
	}

	public String getCarRegNum() {
		return this.carRegNum;
	}

	public void setCarRegNum(String carRegNum) {
		this.carRegNum = carRegNum;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDefaultHomeAddress() {
		return this.defaultHomeAddress;
	}

	public void setDefaultHomeAddress(String defaultHomeAddress) {
		this.defaultHomeAddress = defaultHomeAddress;
	}

	public String getDefaultHomeLandmark() {
		return this.defaultHomeLandmark;
	}

	public void setDefaultHomeLandmark(String defaultHomeLandmark) {
		this.defaultHomeLandmark = defaultHomeLandmark;
	}

	public String getDefaultOfficeAddress() {
		return this.defaultOfficeAddress;
	}

	public void setDefaultOfficeAddress(String defaultOfficeAddress) {
		this.defaultOfficeAddress = defaultOfficeAddress;
	}

	public String getDefaultOfficeLandmark() {
		return this.defaultOfficeLandmark;
	}

	public void setDefaultOfficeLandmark(String defaultOfficeLandmark) {
		this.defaultOfficeLandmark = defaultOfficeLandmark;
	}

	public String getEmail() {
		return this.email;
	}

	public boolean isOtpVerified() {
		return otpVerified;
	}

	public void setOtpVerified(boolean otpVerified) {
		this.otpVerified = otpVerified;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Timestamp getLeavingTime() {
		return this.leavingTime;
	}

	public void setLeavingTime(Timestamp leavingTime) {
		this.leavingTime = leavingTime;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getNumOfSeatsShare() {
		return numOfSeatsShare;
	}

	public void setNumOfSeatsShare(int numOfSeatsShare) {
		this.numOfSeatsShare = numOfSeatsShare;
	}

	public String getOtp() {
		return this.otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSourceToggle() {
		return this.sourceToggle;
	}

	public void setSourceToggle(String sourceToggle) {
		this.sourceToggle = sourceToggle;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getUserImagePath() {
		return this.userImagePath;
	}

	public void setUserImagePath(String userImagePath) {
		this.userImagePath = userImagePath;
	}

	public PushNotificationCrd getPushNotificationCrd() {
		return pushNotificationCrd;
	}

	public void setPushNotificationCrd(PushNotificationCrd pushNotificationCrd) {
		this.pushNotificationCrd = pushNotificationCrd;
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