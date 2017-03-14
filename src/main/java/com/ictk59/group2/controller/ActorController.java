package com.ictk59.group2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ictk59.group2.domain.Actor;
import com.ictk59.group2.service.ActorService;

@Controller
@RequestMapping("/actor")
public class ActorController {

	private ActorService actorService;

	@Autowired
	public ActorController(ActorService actorService) {
		super();
		this.actorService = actorService;
	}
	
	@RequestMapping("/{actorId}")
	@ResponseBody
	public String view(@PathVariable("actorId") Long id){
		Actor actor = actorService.getActorById(id);
		return actor.toString();
	}
	
	
}
