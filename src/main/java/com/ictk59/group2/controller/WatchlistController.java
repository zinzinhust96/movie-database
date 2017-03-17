package com.ictk59.group2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class WatchlistController {
	
	@RequestMapping("/")
	@ResponseBody
	public String watchlist(){
		return "Watch list";
	}
	
}
