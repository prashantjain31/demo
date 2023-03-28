package com.example.demo.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Company;
import com.example.demo.model.Employee;
import com.example.demo.model.Team;
import com.example.demo.repo.CompanyRepo;
import com.example.demo.repo.EmployeeRepo;
import com.example.demo.repo.TeamRepo;

@Service
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
		System.out.println("inside manager");
		Team team = teamRepo.findById(teamId).orElse(null);
		System.out.println("team found");
		if(team != null) {
			Employee employee = employeeRepo.findById(employeeId).orElse(null);
			System.out.println("employee found");
			if(employee != null) {
				Company teamCom = companyRepo.findById(team.getCompany()).orElse(null);
				Company employeeCom = companyRepo.findById(employee.getCompany()).orElse(null);
				System.out.println("teamCOm & employeeCom found");
				if(teamCom != null && employeeCom != null && teamCom.getId() == employeeCom.getId()) {
					System.out.println("inside if");
					employee.setJoinedTeam(team);
//					employeeRepo.save(employee);
//					return teamRepo.findById(teamId).orElse(null);
					team.setJoin(employee);
					return teamRepo.save(team);
				}
			}
		}
		return null;
	}
}
