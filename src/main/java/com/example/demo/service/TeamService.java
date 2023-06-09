package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.helpers.ResourceNotFoundExceptionHandler;
import com.example.demo.manager.TeamManager;
import com.example.demo.model.Employee;
import com.example.demo.model.Team;
import com.example.demo.repo.CompanyRepo;
import com.example.demo.repo.EmployeeRepo;
import com.example.demo.repo.TeamRepo;

@Service
public class TeamService {

	@Autowired
	TeamRepo teamRepo;
	@Autowired
	TeamManager teamManager;
	
	public List<Team> getTeams() {
		return teamRepo.findAll();
	}
	@Cacheable("teams")
	public Team addTeam(Team team) {
		return teamRepo.save(team);
	}

	public void deleteTeams() {
		teamRepo.deleteAll();
	}
	
//	@Cacheable("teams")
	public Team getTeam(Long teamId) {
		if(teamId != null) {
			return teamRepo.findById(teamId).orElseThrow(() -> new ResourceNotFoundExceptionHandler("Team with id: " + teamId + " not found"));
		}
		return null;
	}
//	@CachePut(value="teams", key="#teamId")
	public Team updateTeam(Long teamId, Team team) {
		if(teamId != null) {
			Team oldTeam = teamRepo.findById(teamId).orElseThrow(() -> new ResourceNotFoundExceptionHandler("Team with id: " + teamId + " not found"));
			if(oldTeam != null) {
//				oldTeam.setJoins(team.getJoins());
				oldTeam.setTeamName(team.getTeamName());
				return teamRepo.save(oldTeam);
			}
		}
		return null;
	}
//	@CacheEvict("teams")
	public void deleteTeam(Long teamId) {
		if(teamId != null) {
			teamRepo.deleteById(teamId);
		}
	}
	
//	@CachePut(value="teams", key="#teamId")
	public Team addEmployee(Long teamId, Long employeeId) {
		System.out.println("inside service");
		return teamManager.addEmployee(teamId, employeeId);
	}
	
//	@CachePut(value="teams", key="#teamId") 
	public Team addCompany(Long teamId, Long companyId){
		return teamManager.addCompany(teamId, companyId);
	}

}
