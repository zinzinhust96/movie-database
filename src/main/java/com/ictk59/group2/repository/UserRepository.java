package com.ictk59.group2.repository;

import org.springframework.data.repository.CrudRepository;

import com.ictk59.group2.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);
	
}
