package com.example.dal.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the ryde_ryder_request database table.
 * 
 */
@Entity
@Table(name="ryde_ryder_request")
public class RydeRyderRequest implements Serializable,Comparable<RydeRyderRequest> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	private String destination;

	@Column(name="destination_latitude")
	private double destinationLatitude;

	@Column(name="destination_longitude")
	private double destinationLongitude;

	private String source;

	@Column(name="source_latitude")
	private double sourceLatitude;

	@Column(name="source_longitude")
	private double sourceLongitude;

	@Temporal(TemporalType.TIME)
	@Column(name="start_time")
	private Date startTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;
	
	@Column(name="user_type")
	private String userType;

	//bi-directional many-to-one association to RydeAcceptedRejected
	@JsonIgnore
	@OneToMany(mappedBy="rydeRyderRequest")
	private List<RydeAcceptedRejected> rydeAcceptedRejecteds;

	//bi-directional many-to-one association to RydeAccomplished
	@JsonIgnore
	@OneToMany(mappedBy="rydeRyderRequest")
	private List<RydeAccomplished> rydeAccomplisheds;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Transient
	private double distanceFromSource;
	
	public RydeRyderRequest() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getDestination() {
		return this.destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public double getDestinationLatitude() {
		return this.destinationLatitude;
	}

	public void setDestinationLatitude(double destinationLatitude) {
		this.destinationLatitude = destinationLatitude;
	}

	public double getDestinationLongitude() {
		return this.destinationLongitude;
	}

	public void setDestinationLongitude(double destinationLongitude) {
		this.destinationLongitude = destinationLongitude;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public double getSourceLatitude() {
		return this.sourceLatitude;
	}

	public void setSourceLatitude(double sourceLatitude) {
		this.sourceLatitude = sourceLatitude;
	}

	public double getSourceLongitude() {
		return this.sourceLongitude;
	}

	public void setSourceLongitude(double sourceLongitude) {
		this.sourceLongitude = sourceLongitude;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<RydeAcceptedRejected> getRydeAcceptedRejecteds() {
		return this.rydeAcceptedRejecteds;
	}

	public void setRydeAcceptedRejecteds(List<RydeAcceptedRejected> rydeAcceptedRejecteds) {
		this.rydeAcceptedRejecteds = rydeAcceptedRejecteds;
	}

	public RydeAcceptedRejected addRydeAcceptedRejected(RydeAcceptedRejected rydeAcceptedRejected) {
		getRydeAcceptedRejecteds().add(rydeAcceptedRejected);
		rydeAcceptedRejected.setRydeRyderRequest(this);

		return rydeAcceptedRejected;
	}

	public RydeAcceptedRejected removeRydeAcceptedRejected(RydeAcceptedRejected rydeAcceptedRejected) {
		getRydeAcceptedRejecteds().remove(rydeAcceptedRejected);
		rydeAcceptedRejected.setRydeRyderRequest(null);

		return rydeAcceptedRejected;
	}

	public List<RydeAccomplished> getRydeAccomplisheds() {
		return this.rydeAccomplisheds;
	}

	public void setRydeAccomplisheds(List<RydeAccomplished> rydeAccomplisheds) {
		this.rydeAccomplisheds = rydeAccomplisheds;
	}

	public RydeAccomplished addRydeAccomplished(RydeAccomplished rydeAccomplished) {
		getRydeAccomplisheds().add(rydeAccomplished);
		rydeAccomplished.setRydeRyderRequest(this);

		return rydeAccomplished;
	}

	public RydeAccomplished removeRydeAccomplished(RydeAccomplished rydeAccomplished) {
		getRydeAccomplisheds().remove(rydeAccomplished);
		rydeAccomplished.setRydeRyderRequest(null);

		return rydeAccomplished;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}


	public double getDistanceFromSource() {
		return distanceFromSource;
	}

	public void setDistanceFromSource(double distanceFromSource) {
		this.distanceFromSource = distanceFromSource;
	}

	@Override
	public int compareTo(RydeRyderRequest o) {
		return this.getDistanceFromSource()<o.getDistanceFromSource()?-1:this.getDistanceFromSource()>o.getDistanceFromSource()?1:0;
	}
	
	

}