package com.app.registersystem.validation;

import java.util.Objects;

import com.app.registersystem.dto.UserDTO;
import com.app.registersystem.exception.DataNotFoundException;
import com.app.registersystem.exception.DataValidationEaxception;

public class DtoValidationUtils {

	public enum OPTINALITY {
		REQUIRED, OPTIONAL;
	}

	public static void validateUser(UserDTO dto) {
		if (Objects.isNull(dto)) {

			throw new DataNotFoundException("no data to process");

		}
		validateRequired(dto.getUserName(), OPTINALITY.REQUIRED);
		validateRequired(dto.getApartmentNumber(), OPTINALITY.REQUIRED);
		validateRequired(dto.getAddress(),OPTINALITY.REQUIRED);
	}

	public static void validateRequired(Object field, OPTINALITY optionality) {
		if (optionality == OPTINALITY.REQUIRED) {

			if (field == null) {
				throw new DataValidationEaxception("field should not be null!!");
			}
			if (field instanceof String && String.valueOf( field).isEmpty()) {
				throw new DataValidationEaxception("field should not be empty!!");
			}
		}

	}
	
	
}