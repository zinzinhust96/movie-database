package com.ictk59.group2.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@ModelAttribute("movie")
	public Movie newMovie(){
		return new Movie();
	}
	
	@RequestMapping("/")
	public String home(Model model){
		List<Movie> movies = movieService.getMovieOrderByYear();
		model.addAttribute("type", "");
		model.addAttribute("movies", movies);
		return "movie/year-desc";
	}

	@RequestMapping("/{movieId}")
	public String view(@PathVariable("movieId") Long id, Model model){
		Movie movie = movieService.getMovieById(id);
		model.addAttribute("movieProfile", movie);
		return "movie/movie-profile";
	}
	
	@RequestMapping(
		value = "/search",
		params = {"genre", "sort"})
	public String sort(Model model, @RequestParam(value = "genre") String genreType, @RequestParam("sort") String sort){
		model.addAttribute("type", genreType);
		if(sort.equalsIgnoreCase("rating,asc")){
			List<Movie> movies = movieService.getMoviesByGenre(genreType, new Sort(Sort.Direction.ASC, "rating"));
			model.addAttribute("movies", movies);
			return "movie/rating-asc";
		}else if(sort.equalsIgnoreCase("rating,desc")){
			List<Movie> movies = movieService.getMoviesByGenre(genreType, new Sort(Sort.Direction.DESC, "rating"));
			model.addAttribute("movies", movies);
			return "movie/rating-desc";
		}else if(sort.equalsIgnoreCase("year,asc")){
			List<Movie> movies = movieService.getMoviesByGenre(genreType, new Sort(Sort.Direction.ASC, "year"));
			model.addAttribute("movies", movies);
			return "movie/year-asc";
		}else if(sort.equalsIgnoreCase("year,desc")){
			List<Movie> movies = movieService.getMoviesByGenre(genreType, new Sort(Sort.Direction.DESC, "year"));
			model.addAttribute("movies", movies);
			return "movie/year-desc";
		}else{
			return null;
		}
	}
}
