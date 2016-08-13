package com.example.dal.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the user_home_ofc_location database table.
 * 
 */
@Entity
@Table(name="user_home_ofc_location")
public class UserHomeOfcLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="home_latitude")
	private double homeLatitude;

	@Column(name="home_longitude")
	private double homeLongitude;

	@Column(name="office_latitude")
	private double officeLatitude;

	@Column(name="office_longitude")
	private double officeLongitude;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public UserHomeOfcLocation() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getHomeLatitude() {
		return this.homeLatitude;
	}

	public void setHomeLatitude(double homeLatitude) {
		this.homeLatitude = homeLatitude;
	}

	public double getHomeLongitude() {
		return this.homeLongitude;
	}

	public void setHomeLongitude(double homeLongitude) {
		this.homeLongitude = homeLongitude;
	}

	public double getOfficeLatitude() {
		return this.officeLatitude;
	}

	public void setOfficeLatitude(double officeLatitude) {
		this.officeLatitude = officeLatitude;
	}

	public double getOfficeLongitude() {
		return this.officeLongitude;
	}

	public void setOfficeLongitude(double officeLongitude) {
		this.officeLongitude = officeLongitude;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}