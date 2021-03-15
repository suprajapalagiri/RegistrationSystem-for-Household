package com.app.registersystem.service;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.registersystem.model.User;

@Service
public interface UserService {
public User saveUser(User user);
public List<User> getByDate(Instant date);


}
