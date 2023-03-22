package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.LongRequest;
import com.example.demo.model.Team;
import com.example.demo.service.TeamService;

@RestController
@RequestMapping("teams")
public class TeamController {
	
	@Autowired
	TeamService teamService;
	
	@GetMapping("/")
	public List<Team> getTeams() {
		return teamService.getTeams();
	}
	@PostMapping("/")
	public Team addTeam(@RequestBody Team team) {
		return teamService.addTeam(team);
	}
	@DeleteMapping("/")
	public void deleteTeams() {
		teamService.deleteTeams();
	}
	
	@GetMapping("/{teamId}")
	public Team getTeam(@PathVariable("teamId") Long teamId) {
		if(teamId != null) {
			return teamService.getTeam(teamId);
		}
		return null;
	}
	@PutMapping("/{teamId}")
	public Team updateTeam(@PathVariable("teamId") Long teamId, @RequestBody Team team) {
		if(teamId != null) {
			return teamService.updateTeam(teamId, team);
		}
		return null;
	}
	@DeleteMapping("/{teamId}")
	public void deleteTeam(@PathVariable("teamId") Long teamId) {
		if(teamId != null) {
			teamService.deleteTeam(teamId);
		}
	}
	
	@PostMapping("/{teamId}/addEmployee")
	public Team addEmployee(@PathVariable("teamId") Long teamId, @RequestBody LongRequest employeeId) {
		if(teamId != null) {
			return teamService.addEmployee(teamId, employeeId.getLongValue());
		}
		return null;
	}
	
}
