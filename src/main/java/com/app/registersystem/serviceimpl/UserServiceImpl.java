package com.app.registersystem.serviceimpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.registersystem.model.User;
import com.app.registersystem.repository.UserRepository;
import com.app.registersystem.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public User saveUser(User user) {
		User save = userRepo.save(user);
		return save;
	}

	@Override
	public List<User> getByDate(LocalDate date) {

		return userRepo.findByinDate(date);
	}

}
