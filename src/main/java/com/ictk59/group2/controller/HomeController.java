package com.ictk59.group2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ictk59.group2.service.MovieService;

@Controller
public class HomeController {
	
	private MovieService movieService;
	
	@Autowired
	public HomeController(MovieService movieService) {
		super();
		this.movieService = movieService;
	}

	@RequestMapping("/")
	public String home(Model model){
		model.addAttribute("movies", movieService.getMovieOrderByYear());
		return "index";
	}
	
}
