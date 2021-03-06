package com.ictk59.group2.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ictk59.group2.domain.Movie;

public interface MovieRepository extends CrudRepository<Movie, Long> {

	List<Movie> findAllByOrderByYearDesc();

	List<Movie> findAllByTitleContainingIgnoreCase(@Param("name") String movieName);
	
	List<Movie> findAllByGenreContainingIgnoreCase(@Param("genre") String genre, @Param("sort") Sort sort);

}
