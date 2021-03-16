package com.app.registersystem.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private Long userId;
	private String userName;
	private String phoneNum;
	private String address;
	private String apartmentNumber;
	private LocalDate inDate;

	private LocalTime inTime;

	private LocalDate outDate;
	@Column(name = "out_time")
	private LocalTime outTime;
 
	private String status;
}
