package com.ictk59.group2.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ictk59.group2.service.SecurityService;

@Controller
@RequestMapping("/user")
public class WatchlistController {
	
	private SecurityService securityService;
	
	@Autowired
	public WatchlistController(SecurityService securityService) {
		super();
		this.securityService = securityService;
	}

	@RequestMapping("/")
	@ResponseBody
	public String watchlist(Principal principal){
		return principal.getName();
	}
	
}
