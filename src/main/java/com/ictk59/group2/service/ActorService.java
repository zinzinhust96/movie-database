package com.ictk59.group2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ictk59.group2.domain.Actor;
import com.ictk59.group2.repository.ActorRepository;

@Service
public class ActorService {

	private ActorRepository actorRepo;

	@Autowired
	public ActorService(ActorRepository actorRepo) {
		super();
		this.actorRepo = actorRepo;
	}

	public List<Actor> getActorsByName(String name) {
		return actorRepo.findAllByNameContainingIgnoreCase(name);
	}

	public Actor getActorById(Long id) {
		return actorRepo.findOne(id);
	}
	
}
