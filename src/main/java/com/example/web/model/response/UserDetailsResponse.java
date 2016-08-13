package com.example.web.model.response;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


public class UserDetailsResponse extends User {
	private static final long serialVersionUID = 1L;
	
	private String carImagePath;

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

	private boolean otpVerified;

	private String sourceToggle;

	private Boolean termCondChecked;

	private String userImagePath;

	private String email;
	
	private String userType;
	
	private int id;
	

	public UserDetailsResponse(com.example.dal.entity.User user,boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
			
		super(user.getEmail(),user.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCarImagePath() {
		return carImagePath;
	}


	public void setCarImagePath(String carImagePath) {
		this.carImagePath = carImagePath;
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

	public boolean isOtpVerified() {
		return otpVerified;
	}


	public void setOtpVerified(boolean otpVerified) {
		this.otpVerified = otpVerified;
	}


	public String getSourceToggle() {
		return sourceToggle;
	}


	public void setSourceToggle(String sourceToggle) {
		this.sourceToggle = sourceToggle;
	}


	public Boolean getTermCondChecked() {
		return termCondChecked;
	}


	public void setTermCondChecked(Boolean termCondChecked) {
		this.termCondChecked = termCondChecked;
	}


	public String getUserImagePath() {
		return userImagePath;
	}


	public void setUserImagePath(String userImagePath) {
		this.userImagePath = userImagePath;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}

}