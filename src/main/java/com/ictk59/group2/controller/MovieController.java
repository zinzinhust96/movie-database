package com.ictk59.group2.controller;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ictk59.group2.domain.Movie;
import com.ictk59.group2.domain.User;
import com.ictk59.group2.service.MovieService;
import com.ictk59.group2.service.UserService;

@Controller
@RequestMapping("/movie")
public class MovieController {
	
	private MovieService movieService;
	private UserService userService;
			
	@Autowired
	public MovieController(MovieService movieService, UserService userService) {
		super();
		this.movieService = movieService;
		this.userService = userService;
	}
	
	@ModelAttribute("movie")
	public Movie newMovie(){
		return new Movie();
	}
	
	@RequestMapping("/")
	public String home(Model model){
		List<Movie> movies = movieService.getMovieOrderByYear();
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//	    String name = auth.getName(); //get logged in username
//	    System.out.println(name);
		model.addAttribute("type", "");
		model.addAttribute("movies", movies);
		return "sort/year-desc";
	}

	@RequestMapping("/{movieId}")
	public String view(@PathVariable("movieId") Long id, Model model){
		Movie movie = movieService.getMovieById(id);
		model.addAttribute("movieProfile", movie);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName(); //get logged in username
	    if(username.equals("anonymousUser")){
	    	return "movie/movie-profile-add";
	    }else{
	    	User currentUser = userService.findByUsername(username);
	    	Set<Movie> currentWatchlist = currentUser.getWatchlist();
	    	if(currentWatchlist.contains(movie)){
	    		return "movie/movie-profile-remove";
	    	}else{
	    		return "movie/movie-profile-add";
	    	}
	    }
	}
	
	@RequestMapping(
		value = "/search",
		params = {"genre", "sort"})
	public String sort(Model model, @RequestParam(value = "genre") String genreType, @RequestParam("sort") String sort){
		model.addAttribute("type", genreType);
		if(sort.equalsIgnoreCase("rating,asc")){
			List<Movie> movies = movieService.getMoviesByGenre(genreType, new Sort(Sort.Direction.ASC, "rating"));
			model.addAttribute("movies", movies);
			return "sort/rating-asc";
		}else if(sort.equalsIgnoreCase("rating,desc")){
			List<Movie> movies = movieService.getMoviesByGenre(genreType, new Sort(Sort.Direction.DESC, "rating"));
			model.addAttribute("movies", movies);
			return "sort/rating-desc";
		}else if(sort.equalsIgnoreCase("year,asc")){
			List<Movie> movies = movieService.getMoviesByGenre(genreType, new Sort(Sort.Direction.ASC, "year"));
			model.addAttribute("movies", movies);
			return "sort/year-asc";
		}else if(sort.equalsIgnoreCase("year,desc")){
			List<Movie> movies = movieService.getMoviesByGenre(genreType, new Sort(Sort.Direction.DESC, "year"));
			model.addAttribute("movies", movies);
			return "sort/year-desc";
		}else{
			return null;
		}
	}
}
