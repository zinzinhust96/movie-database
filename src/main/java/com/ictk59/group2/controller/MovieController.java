package com.ictk59.group2.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

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
	
	public static List<Movie> movies;
		
	public static String type;

	@Autowired
	public MovieController(MovieService movieService) {
		super();
		this.movieService = movieService;
	}
	
	@ModelAttribute("movie")
	public Movie newMovie(){
		return new Movie();
	}

	@RequestMapping("/{movieId}")
	@ResponseBody
	public String view(@PathVariable("movieId") Long id){
		Movie movie = movieService.getMovieById(id);
		System.out.println(movie.getActors());
		return movie.toString();
	}
	
	@RequestMapping("/sort-rating-asc")
	public String sortRatingAsc(Model model){
		model.addAttribute("type", type);
		Collections.sort(movies, new Comparator<Movie>() {
			@Override
			public int compare(Movie m1, Movie m2) {
				return m1.getRating().compareTo(m2.getRating());
			}
		});
		model.addAttribute("movies", movies);
		return "movie/rating-asc";
	}
	
	@RequestMapping("/sort-rating-desc")
	public String sortRatingDesc(Model model){
		model.addAttribute("type", type);
		Collections.sort(movies, new Comparator<Movie>() {
			@Override
			public int compare(Movie m1, Movie m2) {
				return m2.getRating().compareTo(m1.getRating());
			}
		});
		model.addAttribute("movies", movies);
		return "movie/rating-desc";
	}
	
	@RequestMapping("/sort-year-asc")
	public String sortYearAsc(Model model){
		model.addAttribute("type", type);
		Collections.sort(movies, new Comparator<Movie>() {
			@Override
			public int compare(Movie m1, Movie m2) {
				return m1.getYear().compareTo(m2.getYear());
			}
		});
		model.addAttribute("movies", movies);
		return "movie/year-asc";
	}
	
	@RequestMapping("/sort-year-desc")
	public String sortYearDesc(Model model){
		model.addAttribute("type", type);
		Collections.sort(movies, new Comparator<Movie>() {
			@Override
			public int compare(Movie m1, Movie m2) {
				return m2.getYear().compareTo(m1.getYear());
			}
		});
		model.addAttribute("movies", movies);
		return "movie/year-desc";
	}
	
	@RequestMapping("/genre/{type}")
	public String topByGenre(@PathVariable String type, Model model){
		MovieController.type = type;
		movies = movieService.getMovieByGenreOrderByRating(type);
		model.addAttribute("type", type);
		model.addAttribute("movies", movies);
		return "movie/rating-desc";
	}
	
}
