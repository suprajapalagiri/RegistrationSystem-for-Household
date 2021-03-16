package com.app.registersystem.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.app.registersystem.dto.ErrorDto;
import com.app.registersystem.dto.UserDTO;
import com.app.registersystem.exception.DataNotFoundException;
import com.app.registersystem.exception.DataValidationEaxception;
import com.app.registersystem.model.User;
import com.app.registersystem.service.UserService;
import com.app.registersystem.transformer.UserTransformer;
import com.app.registersystem.validation.DtoValidationUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Api(value="User controller",description = "Rest Api related to User entity!!")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
	@Autowired
	private UserService userService;

	
	
	@ApiOperation(value="adding person into records")
	@ApiResponses(value = {
			@ApiResponse(code = 200,message = "person added successfully by user"),
			@ApiResponse(code = 400, message = "bad request occured due to some url request..Please check once")
	})
	@PostMapping("/createUser")
	public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO) throws URISyntaxException {
		DtoValidationUtils.validateUser(userDTO);

		User user = UserTransformer.fromDtoToEntity(userDTO);

		User saveUser = userService.saveUser(user);
		return ResponseEntity.created(new URI("user/createUser")).body(UserTransformer.fromEntityToDTO(saveUser));
	}

	@ApiOperation(value = "view a list of persons based on date" )
	@ApiResponses(value = {
			@ApiResponse(code = 200,message = "pesrons list retrieved successfully based on date"),
			@ApiResponse(code = 400, message = "bad request occured due to some url request..Please check once")
	})
	
	
	@GetMapping("/getByDate/{date}")
	public ResponseEntity<?> getByDate(@PathVariable String date) throws ParseException {

		LocalDate parsedDate = LocalDate.parse(date);
		List<User> byDate = userService.getByDate(parsedDate);
		//return new ResponseEntity<List<User>>(byDate, HttpStatus.OK);
		return ResponseEntity.ok(byDate);

	}
	@ApiOperation(value = "To get the Person details based on Name" )
	@ApiResponses(value = {
			@ApiResponse(code = 200,message = "pesron retrieved successfully based on name"),
			@ApiResponse(code = 400, message = "bad request occured due to some url request..Please check once")
	})
	
	
	@GetMapping("/getByName/{name}")
	public ResponseEntity<?> getBynamee(@PathVariable String name) throws ParseException {

	User persons = userService.getByName(name);
		return ResponseEntity.ok(persons);

	}
	
	// Exception Handlers

		@ExceptionHandler(DataValidationEaxception.class)
		public ResponseEntity<ErrorDto> dataValidationExceptionHandler(WebRequest httpRequest,
				DataValidationEaxception dataValidationException) {

			return handle(dataValidationException, HttpStatus.BAD_REQUEST, getRequestUri(httpRequest));
		}

		@ExceptionHandler(DataNotFoundException.class)
		public ResponseEntity<ErrorDto> dataNotFoundExceptionHandler(WebRequest httpRequest,
				DataNotFoundException dataNotFoundException) {

			return handle(dataNotFoundException, HttpStatus.BAD_REQUEST, getRequestUri(httpRequest));

		}

		private String getRequestUri(WebRequest request) {
			String uri = null;
			if (request instanceof ServletWebRequest) {
				uri = String.valueOf(((ServletWebRequest) request).getHttpMethod());
				if (((ServletWebRequest) request).getRequest() != null) {
					uri += " " + ((ServletWebRequest) request).getRequest().getRequestURI();
				}
			}
			return uri;
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		protected ResponseEntity<ErrorDto> handle(Throwable t, HttpStatus httpStatus, String requestUri) {
			String type = t.getClass().getSimpleName();
			String description = t.getMessage() != null ? t.getMessage() : "Unknown error";
			if (httpStatus.is5xxServerError())
				log.error(String.format("Encountered unexpected error (code: %s, type: %s, message: %s, uri: %s)",
						httpStatus, type, t.getMessage(), requestUri), t);
			else if (httpStatus != HttpStatus.NOT_FOUND)
				log.warn(String.format("Encountered unexpected error (code: %s, type: %s, message: %s, uri: %s)",
						httpStatus, type, t.getMessage(), requestUri));
			ErrorDto error = new ErrorDto();
			error.setStatusCode(httpStatus.value());
			error.setType(type);
			error.setDescription(description);
			return new ResponseEntity(error, httpStatus);
		}

	}
