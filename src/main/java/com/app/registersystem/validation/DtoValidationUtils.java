package com.app.registersystem.validation;

import java.util.Objects;

import com.app.registersystem.controller.UserDTO;

public class DtoValidationUtils {

	public enum OPTINALITY {
		REQUIRED, OPTIONAL;
	}

	public static void validateUser(UserDTO dto) {
		if (Objects.isNull(dto)) {

			throw new DataNotFoundException("no data to process");

		}

	}

}
