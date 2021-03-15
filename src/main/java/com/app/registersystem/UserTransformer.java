package com.app.registersystem;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import com.app.registersystem.controller.UserDTO;
import com.app.registersystem.model.User;
import com.app.registersystem.validation.DataNotFoundException;

public class UserTransformer {

	public static User fromDtoToEntity(UserDTO dto) {
		if (Objects.isNull(dto)) {
			throw new DataNotFoundException("user data not found");
		}

		return User.builder().address(dto.getAddress()).apartmentNum(dto.getApartmentNumber()).inDate(LocalDate.now())
				.inTime(LocalTime.now()).outDate(dto.getOutDate()).outTime(dto.getOutTime()).phoneNum(dto.getPhoneNum())
				.userId(dto.getUserId()).userName(dto.getUserName()).build();

	}

	public static UserDTO fromEntityToDTO(User user) {
		if (Objects.isNull(user)) {
			throw new DataNotFoundException("user data not found");
		}

		return UserDTO.builder().address(user.getAddress()).apartmentNumber(user.getApartmentNum())
				.inDate(LocalDate.now()).inTime(LocalTime.now()).outDate(user.getOutDate()).outTime(user.getOutTime())
				.phoneNum(user.getPhoneNum()).userId(user.getUserId()).userName(user.getUserName()).build();

	}

}
