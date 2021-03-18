package com.app.registersystem.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.app.registersystem.exception.DataValidationEaxception;
import com.app.registersystem.model.User;
import com.app.registersystem.service.UserService;

public class UserControllerTest {
	@InjectMocks
	UserController controller;

	@Mock
	UserService service;

	LocalDate date = LocalDate.of(2021, 03, 15);

	@Before
	public void beforeEach() {
		MockitoAnnotations.initMocks(this);
		controller = new UserController(service);
	}

	public List<User> getUsers() {
		User user = new User(1l, "nagarani", "2165465", "sghd", "54", date, null, null, null, true, false);
		List<User> listUsers = new ArrayList<>();
		listUsers.add(user);
		return listUsers;
	}

	@Test
	public void testSaveUser() {

	}

	@Test
	public void testGetByDate() {

		when(service.getByDate(date)).thenReturn(getUsers());
		ResponseEntity<?> byDate = controller.getByDate("2021-03-15");
		assertEquals(getUsers(), byDate.getBody());
		assertEquals(byDate.getStatusCode(), HttpStatus.OK);

	}

	@Test
	public void testGetByDateNullCondition() {
		assertThrows(DataValidationEaxception.class, () -> controller.getByDate(null));
	}
	
	@Test
	public void testGetByDateEmptyCondition() {
		assertThrows(DataValidationEaxception.class, () -> controller.getByDate(""));
	}

}
