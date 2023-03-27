package com.example.demo.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Employee;
import com.example.demo.model.Team;
import com.example.demo.repo.CompanyRepo;
import com.example.demo.repo.EmployeeRepo;
import com.example.demo.repo.TeamRepo;

public class TeamManager {
	
	@Autowired
	TeamRepo teamRepo;
	@Autowired
	EmployeeRepo employeeRepo;
	@Autowired
	CompanyRepo companyRepo;
	
	public Team addCompany(Long teamId, Long companyId){
		Team team = teamRepo.findById(teamId).orElse(null);
		if(team != null) {
			team.setCompany(companyRepo.findById(companyId).orElse(null));
			return teamRepo.save(team);
		}
		return null;
	}
	
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
}
