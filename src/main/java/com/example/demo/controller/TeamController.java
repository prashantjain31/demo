package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Team;
import com.example.demo.repo.TeamRepo;

@RestController
public class TeamController {
	
	@Autowired
	TeamRepo repo;
	
	@PostMapping("addTeam")
	public void addTeam(@RequestBody Team team) {
		repo.save(team);
	}
	
}
