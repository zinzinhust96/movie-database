package com.ictk59.group2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ictk59.group2.domain.Actor;
import com.ictk59.group2.domain.Movie;
import com.ictk59.group2.service.ActorService;
import com.ictk59.group2.service.MovieService;

@Controller
public class HomeController {
	
	private MovieService movieService;
	
	private ActorService actorServices;
	
	@Autowired
	public HomeController(MovieService movieService, ActorService actorService) {
		super();
		this.movieService = movieService;
		this.actorServices = actorService;
	}
	
	@ModelAttribute("movie")
	public Movie newMovie(){
		return new Movie();
	}

	@RequestMapping("/")
	public String home(Model model){
		model.addAttribute("movie", new Movie());
		
		MovieController.type = "";
				
		MovieController.movies = movieService.getMovieOrderByYear();
		model.addAttribute("movies", MovieController.movies);
		return "movie/year-desc";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(@ModelAttribute("movie") Movie movie, Model model){
		String searchedText = movie.getTitle();
		List<Movie> movies = movieService.getMoviesByTitle(searchedText);
		List<Actor> actors = actorServices.getActorsByName(searchedText);
		
		model.addAttribute("searchedText", searchedText);
		model.addAttribute("movies", movies);
		model.addAttribute("actors", actors);
		return "index";
	}
}
