package com.app.registersystem.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.app.registersystem.dto.UserDTO;
import com.app.registersystem.exception.DataNotFoundException;
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

	public UserDTO getUser() {
		return new UserDTO(1l, "nagarani", "2165465", "sghd", "54", date, null, null, null, true, false);
	}

	public List<User> getUsers() {
		User user = new User(1l, "nagarani", "2165465", "sghd", "54", date, null, null, null, true, false);
		List<User> listUsers = new ArrayList<>();
		listUsers.add(user);
		return listUsers;
	}

	@Test
	public void testSaveUser() {
		try {
			when(service.saveUser(getUsers().get(0))).thenReturn(getUsers().get(0));
			ResponseEntity<?> saveUser = controller.saveUser(getUser());
			assertEquals(getUser(), saveUser.getBody());

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSaveUserNull() {
		assertThrows(DataNotFoundException.class, () -> controller.saveUser(null));

	}

	@Test
	public void testSaveUserNameNull() {
		UserDTO user = getUser();
		user.setUserName(null);
		assertThrows(DataValidationEaxception.class, () -> controller.saveUser(user));

	}

	@Test
	public void testSaveUserApartmentNumberNull() {
		UserDTO user = getUser();
		user.setApartmentNumber(null);
		assertThrows(DataValidationEaxception.class, () -> controller.saveUser(user));

	}

	@Test
	public void testSaveUserAddressNull() {
		UserDTO user = getUser();
		user.setAddress(null);
		assertThrows(DataValidationEaxception.class, () -> controller.saveUser(user));

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

	@Test
	public void testGetByName() {

		when(service.getByName("nagarani")).thenReturn(getUsers());
		ResponseEntity<?> byDate = controller.getBynamee("nagarani");
		assertEquals(getUsers(), byDate.getBody());
		assertEquals(byDate.getStatusCode(), HttpStatus.OK);

	}

	@Test
	public void testGetByNameNullCondition() {
		assertThrows(DataValidationEaxception.class, () -> controller.getBynamee(null));
	}

	@Test
	public void testGetByNameEmptyCondition() {
		assertThrows(DataValidationEaxception.class, () -> controller.getBynamee(""));
	}
}
