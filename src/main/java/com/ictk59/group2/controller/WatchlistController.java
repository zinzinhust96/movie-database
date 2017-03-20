package com.ictk59.group2.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ictk59.group2.domain.Movie;
import com.ictk59.group2.domain.User;
import com.ictk59.group2.service.MovieService;
import com.ictk59.group2.service.UserService;

@Controller
@RequestMapping("/user")
public class WatchlistController {
	
	private UserService userService;
	private MovieService movieService;
	
	@Autowired
	public WatchlistController(UserService userService, MovieService movieService) {
		super();
		this.userService = userService;
		this.movieService = movieService;
	}
	
	@ModelAttribute("movie")
	public Movie newMovie(){
		return new Movie();
	}

	@RequestMapping("/watchlist")
	public String watchlist(Model model){
		String username = getLoggedInUsername();
		
		User currentUser = userService.findByUsername(username);
		Set<Movie> movies = currentUser.getWatchlist();
		model.addAttribute("username", username);
		model.addAttribute("movies", movies);
		return "movie/watchlist";
	}
	
	@RequestMapping("/add/{id}")
	public String add(@PathVariable("id") Long id, Model model){
		String username = getLoggedInUsername();
		User currentUser = userService.findByUsername(username);
		Movie movie = movieService.getMovieById(id);
		
		Set<Movie> currentWatchlist = currentUser.getWatchlist();
		currentWatchlist.add(movie);
		currentUser.setWatchlist(currentWatchlist);
		
		userService.saveWatchlist(currentUser);
		return "redirect:/movie/" + id;
	}
	
	@RequestMapping("/remove/{id}")
	public String remove(@PathVariable("id") Long id, Model model){
		String username = getLoggedInUsername();
		User currentUser = userService.findByUsername(username);
		Movie movie = movieService.getMovieById(id);
		
		Set<Movie> currentWatchlist = currentUser.getWatchlist();
		currentWatchlist.remove(movie);
		currentUser.setWatchlist(currentWatchlist);
		
		userService.saveWatchlist(currentUser);
		return "redirect:/movie/" + id;
	}
	
	private String getLoggedInUsername(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}
	
}
