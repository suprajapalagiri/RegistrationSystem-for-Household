package com.app.registersystem.transformer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import com.app.registersystem.dto.UserDTO;
import com.app.registersystem.exception.DataNotFoundException;
import com.app.registersystem.model.User;

public class UserTransformer {

	public static User fromDtoToEntity(UserDTO dto) {
		if (Objects.isNull(dto)) {
			throw new DataNotFoundException("user data not found");
		}

		return User.builder().address(dto.getAddress()).apartmentNum(dto.getApartmentNumber()).inDate(dto.getInDate())
				.inTime(dto.getInTime()).outDate(dto.getOutDate()).outTime(dto.getOutTime()).phoneNum(dto.getPhoneNum())
				.userId(dto.getUserId()).userName(dto.getUserName()).enteringStatus(dto.isEnteringStatus()).leavingStatus(dto.isLeavingStatus()).build();

	}

	public static UserDTO fromEntityToDTO(User user) {
		if (Objects.isNull(user)) {
			throw new DataNotFoundException("user data not found");
		}

		return UserDTO.builder().address(user.getAddress()).apartmentNumber(user.getApartmentNum())
				.inDate(user.getInDate()).inTime(user.getInTime()).outDate(user.getOutDate()).outTime(user.getOutTime())
				.phoneNum(user.getPhoneNum()).userId(user.getUserId()).userName(user.getUserName()).enteringStatus(user.isEnteringStatus()).leavingStatus(user.isLeavingStatus()).build();

	}

}
