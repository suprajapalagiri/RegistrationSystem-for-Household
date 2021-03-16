package com.app.registersystem.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.registersystem.UserTransformer;
import com.app.registersystem.model.User;
import com.app.registersystem.service.UserService;
import com.app.registersystem.validation.DtoValidationUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value="User controller",description = "Rest Api related to User entity!!")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	
	@ApiOperation(value="adding person into records")
	@ApiResponses(value = {
			@ApiResponse(code = 200,message = "Success"),
			@ApiResponse(code = 401, message = "not authorized!"),
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!")
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
			@ApiResponse(code = 200,message = "Success"),
			@ApiResponse(code = 401, message = "not authorized!"),
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!")
	})
	
	
	@GetMapping("/getByDate/{date}")
	public ResponseEntity<List<User>> getByDate(@PathVariable String date) throws ParseException {

		LocalDate parsedDate = LocalDate.parse(date);
		List<User> byDate = userService.getByDate(parsedDate);
		return new ResponseEntity<List<User>>(byDate, HttpStatus.OK);

	}

}
