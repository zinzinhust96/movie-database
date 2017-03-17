package com.ictk59.group2.service;

public interface SecurityService {

	String findLoggedInUsername();

    void autologin(String username, String password);
	
}
