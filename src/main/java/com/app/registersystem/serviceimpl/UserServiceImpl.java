package com.app.registersystem.serviceimpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.registersystem.exception.DataNotFoundException;
import com.app.registersystem.model.User;
import com.app.registersystem.repository.UserRepository;
import com.app.registersystem.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public User saveUser(User user) {
		User save = null;
		List<User> byName = getByName(user.getUserName());
		if (byName.isEmpty()) {
			user.setInTime(LocalTime.now());
			user.setInDate(LocalDate.now());
			save = userRepo.save(user);
		} else {
			User user2 = byName.get(byName.size() - 1);
			if (user.isEnteringStatus() && !user.isLeavingStatus()) {
				user.setInTime(LocalTime.now());
				user.setInDate(LocalDate.now());
				save = userRepo.save(user);
			}
			if (user.isEnteringStatus() && user.isLeavingStatus()) {
				System.out.println("user id::" + user2.getUserId());
				user.setUserId(user2.getUserId());
				user.setInDate(user2.getInDate());
				user.setInTime(user2.getInTime());
				user.setOutDate(LocalDate.now());
				user.setOutTime(LocalTime.now());
				save = userRepo.save(user);
			}

		} 
		return save;
	}

	@Override
	public List<User> getByDate(LocalDate date) {
		List<User> findByDate = userRepo.findByinDate(date);
		if (findByDate.isEmpty())
			throw new DataNotFoundException("No Records Found With The Given Date");
		else
			return findByDate;
	}

	@Override
	public List<User> getByName(String name) {

		List<User> findByuserName = userRepo.findByuserName(name);
		if (findByuserName.isEmpty())
			throw new DataNotFoundException("No Records Found With The Given Name");
		else
			return findByuserName;
	}

}
