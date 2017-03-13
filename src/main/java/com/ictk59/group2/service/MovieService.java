package com.ictk59.group2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ictk59.group2.domain.Movie;
import com.ictk59.group2.repository.MovieRepository;

@Service
public class MovieService {
	
	private MovieRepository movieRepo;
	
	@Autowired
	public MovieService(MovieRepository movieRepo) {
		super();
		this.movieRepo = movieRepo;
	}

	public List<Movie> getMovieOrderByYear() {
		return movieRepo.findAllByOrderByYearDesc();
	}

	public Movie getMovieById(Long id) {
		return movieRepo.findOne(id);
	}

	public List<Movie> getMoviesByTitle(String movieName) {
		return movieRepo.findAllByTitleContainingIgnoreCase(movieName);
	}

	public List<Movie> getMovieByGenreOrderByRating(String genre) {
		return movieRepo.findAllByGenreContainingIgnoreCaseOrderByRatingDesc(genre);
	}

}
