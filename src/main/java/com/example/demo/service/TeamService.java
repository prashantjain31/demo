package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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
	EmployeeRepo employeeRepo;
	@Autowired
	CompanyRepo companyRepo;
	
//	@Cacheable("teams")
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
	
	@Cacheable("teams")
	public Team getTeam(Long teamId) {
		if(teamId != null) {
			return teamRepo.findById(teamId).orElse(null);
		}
		return null;
	}
	@CachePut(value="teams", key="#teamId")
	public Team updateTeam(Long teamId, Team team) {
		if(teamId != null) {
			Team oldTeam = teamRepo.findById(teamId).orElse(null);
			if(oldTeam != null) {
//				oldTeam.setJoins(team.getJoins());
				oldTeam.setTeamName(team.getTeamName());
				return teamRepo.save(oldTeam);
			}
		}
		return null;
	}
	@CacheEvict("teams")
	public void deleteTeam(Long teamId) {
		if(teamId != null) {
			teamRepo.deleteById(teamId);
		}
	}
	
	@CachePut(value="teams", key="#teamId")
	public Team addEmployee(Long teamId, Long employeeId) {
		Team team = teamRepo.findById(teamId).orElse(null);
		if(team != null) {
			Employee employee = employeeRepo.findById(employeeId).orElse(null);
			if(employee != null) {
				employee.setJoinedTeam(team);
			}
			team.setJoin(employee);
			return teamRepo.save(team);
		}
		return null;
	}
	
	@CachePut(value="teams", key="#teamId") 
	public Team addCompany(Long teamId, Long companyId){
		Team team = teamRepo.findById(teamId).orElse(null);
		if(team != null) {
			team.setCompany(companyRepo.findById(companyId).orElse(null));
			return teamRepo.save(team);
		}
		return null;
	}

}
