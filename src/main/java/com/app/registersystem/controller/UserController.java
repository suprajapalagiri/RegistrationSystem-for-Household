package com.app.registersystem.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.registersystem.model.User;
import com.app.registersystem.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/createUser")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User saveUser = userService.saveUser(user);
		return new ResponseEntity<User>(saveUser, HttpStatus.CREATED);
	}

	@GetMapping("/getByDate/{date}")
	public ResponseEntity<List<User>> getByDate(@PathVariable String date) throws ParseException {
		
		Date requiredDateFormat= new SimpleDateFormat("yyyy-MM-dd").parse(date);
		 Instant reqInstant = requiredDateFormat.toInstant();
		List<User> byDate = userService.getByDate(reqInstant);
		return new ResponseEntity<List<User>>(byDate, HttpStatus.OK);

	}

}
