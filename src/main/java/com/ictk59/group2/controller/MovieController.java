package com.ictk59.group2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping("/")
	public String home(Model model){
		model.addAttribute("movies", movieService.getMovieOrderByYear());
		model.addAttribute("movie", new Movie());
		return "index";
	}

	@RequestMapping("/{movieId}")
	@ResponseBody
	public String view(@PathVariable("movieId") Long id){
		Movie movie = movieService.getMovieById(id);
		return movie.toString();
	}
		
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(@ModelAttribute("movie") Movie movie, Model model){
		System.out.println(movie.getTitle());
		List<Movie> movies = movieService.getMoviesByTitle(movie.getTitle());
		model.addAttribute("movies", movies);
		return "index";
	}
	
}
