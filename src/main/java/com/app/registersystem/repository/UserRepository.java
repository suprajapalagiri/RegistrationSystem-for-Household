package com.app.registersystem.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.registersystem.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "select * from registration  where in_date=?1", nativeQuery = true)

	public List<User> findByinDate(LocalDate date);
	
	/*
	 * @Query(value = "select * from registration  where out_date=?1", nativeQuery =
	 * true)
	 * 
	 * public List<User> findByinDate(LocalDate date);
	 */
	
	@Query(value="select * from registration where name=?1", nativeQuery = true)
	public List<User> findByuserName(String name);

	
}
