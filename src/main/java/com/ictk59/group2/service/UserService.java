package com.ictk59.group2.service;

import com.ictk59.group2.domain.User;

public interface UserService {

	public User findByUsername(String username);
	
	void save(User user);
	
}
