package com.ictk59.group2.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ictk59.group2.domain.Movie;

public interface MovieRepository extends CrudRepository<Movie, Long> {

	List<Movie> findByOrderByYearDesc();

}
