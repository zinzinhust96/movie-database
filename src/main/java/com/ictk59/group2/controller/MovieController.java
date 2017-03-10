package com.ictk59.group2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ictk59.group2.domain.Movie;
import com.ictk59.group2.service.MovieService;

@Controller
@RequestMapping("/movie")
public class MovieController {
	
	private MovieService movieService;
		
	@Autowired
	public MovieController(MovieService movieService) {
		super();
		this.movieService = movieService;
	}

	@RequestMapping("/{movieId}")
	@ResponseBody
	public String view(@PathVariable("movieId") Long id){
		Movie movie = movieService.getMovieById(id);
		return movie.toString();
	}
	
}
