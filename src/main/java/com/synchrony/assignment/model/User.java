package com.synchrony.assignment.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @Description: This class is User model class or entity. Using this class the
 *               USER table created in H2 database. All basic information
 *               including the user's credentials persisted in database.
 * 
 *               Its having one To Many mapping with USERIMAGES table. One user
 *               can upload many images.
 * 
 * @author Aviprash.
 *
 */
@Entity
@Table(name = "USER")
public class User {

	/*
	 * User Id field. This is an identity column in User table.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/*
	 * User Name field. NAME column in USER table in DB.
	 */
	@Column(name = "NAME")
	private String username;

	/*
	 * User password field. PASSWORD column in USER table in DB.
	 */
	@Column(name = "PASSWORD")
	private String password;

	/*
	 * User emailid field. EMAIL column in USER table in DB.
	 */
	@Column(name = "EMAIL")
	private String emailid;

	/*
	 * User gender field. GENDER column in USER table in DB.
	 */
	@Column(name = "GENDER")
	private String gender;

	/*
	 * User permission field. PERMISSION column in USER table in DB.
	 */
	@Column(name = "PERMISSION")
	private String permission;

	/*
	 * One To Many mapping. Set of User Images.
	 */
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<UserImages> userImages;

	/*
	 * Default Constructor
	 */
	public User() {
	}

	/**
	 * Parameterized Constructor.
	 * 
	 * @param username
	 * @param password
	 * @param emailid
	 * @param gender
	 * @param permission
	 */
	@JsonCreator
	public User(@JsonProperty("username") String username, @JsonProperty("password") String password,
			@JsonProperty("emailid") String emailid, @JsonProperty("gender") String gender,
			@JsonProperty("permission") String permission) {
		this.username = username;
		this.password = password;
		this.emailid = emailid;
		this.gender = gender;
		this.permission = permission;

	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the emailid
	 */
	public String getEmailid() {
		return emailid;
	}

	/**
	 * @param emailid
	 *            the emailid to set
	 */
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the permission
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 * @param permission
	 *            the permission to set
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}

	/**
	 * @return the userImages
	 */
	public Set<UserImages> getUserImages() {
		return userImages;
	}

	/**
	 * @param userImages
	 *            the userImages to set
	 */
	public void setUserImages(Set<UserImages> userImages) {
		this.userImages = userImages;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", emailid=" + emailid
				+ ", gender=" + gender + ", permission=" + permission + ", userImages=" + userImages + "]";
	}
}
