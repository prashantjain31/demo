package com.example.demo.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Company;
import com.example.demo.model.Employee;
import com.example.demo.model.Team;
import com.example.demo.repo.CompanyRepo;
import com.example.demo.repo.EmployeeRepo;
import com.example.demo.repo.TeamRepo;

import jakarta.transaction.Transactional;

@Service
public class EmployeeManager {
	
	@Autowired
	EmployeeRepo employeeRepo;
	@Autowired
	CompanyRepo companyRepo;
	@Autowired
	TeamRepo teamRepo;
	
	@Transactional
	public Employee addTeam(Long employeeId, Long teamId) {
		Employee employee = employeeRepo.findById(employeeId).orElse(null);
		if(employee != null) {
			Team team = teamRepo.findById(teamId).orElse(null);
			if(team != null) {
				Company teamCom = companyRepo.findById(team.getCompany()).orElse(null);
				Company employeeCom = companyRepo.findById(employee.getCompany()).orElse(null);
				if(teamCom != null && employeeCom != null && teamCom.getId() == employeeCom.getId()) {
					employee.setJoinedTeam(team);
					return employeeRepo.save(employee);
				}
			}
		}
		return null;
	}
	
	@Transactional
	public Employee addCompany(Long employeeId, Long companyId) {
		Employee employee = employeeRepo.findById(employeeId).orElse(null);
		if(employee != null) {
			employee.setCompany(companyRepo.findById(companyId).orElse(null));
			return employeeRepo.save(employee);
		}
		return null;
	}
}
