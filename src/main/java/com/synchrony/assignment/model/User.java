package com.synchrony.assignment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "AGE")
	private int age;

	@Column(name = "EMAIL")
	private String emailid;

	@Column(name = "GENDER")
	private String gender;

	public User() {
	}

	@JsonCreator
	public User(@JsonProperty("username") String username, @JsonProperty("password") String password,
			@JsonProperty("age") int age, @JsonProperty("emailid") String emailid,
			@JsonProperty("gender") String gender) {
		this.username = username;
		this.password = password;
		this.age = age;
		this.emailid = emailid;
		this.gender = gender;
	}

	/* Setters and Getters */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", emailid=" + emailid
				+ ", gender=" + gender + "]";
	}
}
