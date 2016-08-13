package com.example.dal.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the ryde_accepted_rejected database table.
 * 
 */
@Entity
@Table(name="ryde_accepted_rejected")
public class RydeAcceptedRejected implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="action_user_type")
	private String actionUserType;

	@Column(name="is_accepted")
	private boolean isAccepted;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	//bi-directional many-to-one association to RydeRyderRequest
	@ManyToOne
	@JoinColumn(name="ryde_id")
	private RydeRyderRequest rydeRyderRequest;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	public RydeAcceptedRejected() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActionUserType() {
		return this.actionUserType;
	}

	public void setActionUserType(String actionUserType) {
		this.actionUserType = actionUserType;
	}

	public boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public RydeRyderRequest getRydeRyderRequest() {
		return this.rydeRyderRequest;
	}

	public void setRydeRyderRequest(RydeRyderRequest rydeRyderRequest) {
		this.rydeRyderRequest = rydeRyderRequest;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	

}