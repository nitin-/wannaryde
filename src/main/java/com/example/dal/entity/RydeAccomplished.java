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
 * The persistent class for the ryde_accomplished database table.
 * 
 */
@Entity
@Table(name="ryde_accomplished")
public class RydeAccomplished implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="action_user_type")
	private String actionUserType;

	private String comments;

	@Column(name="is_helpful")
	private byte isHelpful;

	//bi-directional many-to-one association to RydeRyderRequest
	@ManyToOne
	@JoinColumn(name="ryde_id")
	private RydeRyderRequest rydeRyderRequest;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public RydeAccomplished() {
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

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public byte getIsHelpful() {
		return this.isHelpful;
	}

	public void setIsHelpful(byte isHelpful) {
		this.isHelpful = isHelpful;
	}

	public RydeRyderRequest getRydeRyderRequest() {
		return this.rydeRyderRequest;
	}

	public void setRydeRyderRequest(RydeRyderRequest rydeRyderRequest) {
		this.rydeRyderRequest = rydeRyderRequest;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}