package com.app.registersystem.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "registration")
@Data
@Builder
@AllArgsConstructor
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
	@Column(name = "in_date")
	private LocalDate inDate;

	@Column(name = "in_time")
	private LocalTime inTime;

	@Column(name = "out_date")
	private LocalDate outDate;

	@Column(name = "out_time")
	private LocalTime outTime;

	public User() {
		super();
	}

}