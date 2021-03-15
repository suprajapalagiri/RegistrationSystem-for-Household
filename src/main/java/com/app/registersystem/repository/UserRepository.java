package com.app.registersystem.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.registersystem.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public List<User>findByinTime(Instant date);


}
