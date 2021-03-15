package com.app.registersystem.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "Register_Record")
//@Data
//@Setter
public class User {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	@Column(name = "name")
	private String userName;
	@Column(name = "phone_number")
	private String phoneNum;
	@Column(name = "address")
	private String address;
	@Column(name = "apartment_number")
	private String apartmentNum;
	@Column(name = "in_time")
	private Instant inTime;
	@Column(name = "out_time")
	private Instant outTime;

	public User() {
		super();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getApartmentNum() {
		return apartmentNum;
	}

	public void setApartmentNum(String apartmentNum) {
		this.apartmentNum = apartmentNum;
	}

	public Instant getInTime() {
		return inTime;
	}

	public void setInTime(Instant inTime) {
		this.inTime = inTime;
	}

	public Instant getOutTime() {
		return outTime;
	}

	public void setOutTime(Instant outTime) {
		this.outTime = outTime;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", phoneNum=" + phoneNum + ", address=" + address
				+ ", apartmentNum=" + apartmentNum + ", inTime=" + inTime + ", outTime=" + outTime + "]";
	}

}