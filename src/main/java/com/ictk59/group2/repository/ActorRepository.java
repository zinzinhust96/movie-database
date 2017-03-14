package com.ictk59.group2.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ictk59.group2.domain.Actor;

public interface ActorRepository extends CrudRepository<Actor, Long> {

	List<Actor> findAllByNameContainingIgnoreCase(String name);

}
