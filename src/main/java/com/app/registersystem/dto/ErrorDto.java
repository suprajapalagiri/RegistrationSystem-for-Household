package com.app.registersystem.dto;

import lombok.Data;

@Data
public class ErrorDto {
	
	private int statusCode;
	private String type;
	private String description;

}
