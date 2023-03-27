package com.example.demo.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Employee;
import com.example.demo.repo.CompanyRepo;
import com.example.demo.repo.EmployeeRepo;
import com.example.demo.repo.TeamRepo;

public class EmployeeManager {
	
	@Autowired
	EmployeeRepo employeeRepo;
	@Autowired
	CompanyRepo companyRepo;
	@Autowired
	TeamRepo teamRepo;
	
	public Employee addTeam(Long employeeId, Long teamId) {
		Employee employee = employeeRepo.findById(employeeId).orElse(null);
		if(employee != null) {
			employee.setJoinedTeam(teamRepo.findById(teamId).orElse(null));
			return employeeRepo.save(employee);
		}
		return null;
	}
	
	public Employee addCompany(Long employeeId, Long companyId) {
		Employee employee = employeeRepo.findById(employeeId).orElse(null);
		if(employee != null) {
			employee.setCompany(companyRepo.findById(companyId).orElse(null));
			return employeeRepo.save(employee);
		}
		return null;
	}
}
