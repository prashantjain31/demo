package com.example.demo.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.Company;
import com.example.demo.model.Employee;
import com.example.demo.repo.CompanyRepo;
import com.example.demo.repo.EmployeeRepo;
import com.example.demo.repo.TeamRepo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepo employeeRepo;
	@Autowired
	CompanyRepo companyRepo;
	@Autowired
	TeamRepo teamRepo;
	
	public List<Employee> getEmployees() {
		return employeeRepo.findAll();
	}
	@Cacheable("employees")
	public Employee addEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}
	public void deleteEmployees() {
		employeeRepo.deleteAll();
	}
	
	@Cacheable("employees")
	public Employee getEmployee(Long employeeId) {
		return employeeRepo.findById(employeeId).orElse(null);
	}
	@CachePut(value="employees", key="#employeeId")
	public Employee updateEmployee(Long employeeId, Employee employee) {
		Employee emp = employeeRepo.findById(employeeId).orElse(null);
		if(emp != null) {
			emp.setName(employee.getName());
//			emp.setJoinedTeams(employee.getJoinedTeams());
//			emp.setCompany(employee.getCompany());
			emp.setRole(employee.getRole());
			return employeeRepo.save(emp);
		}
		return emp;
	}
	@CacheEvict("employees")
	public void deleteEmployee(Long employeeId) {
		employeeRepo.deleteById(employeeId);
	}
	
	@CachePut(value="employees", key="#employeeId")
	public Employee addCompany(Long employeeId, Long companyId) {
		Employee employee = employeeRepo.findById(employeeId).orElse(null);
		if(employee != null) {
			employee.setCompany(companyRepo.findById(companyId).orElse(null));
			return employeeRepo.save(employee);
		}
		return null;
	}
	
	@CachePut(value="employees", key="#employeeId")
	public Employee addTeam(Long employeeId, Long teamId) {
		Employee employee = employeeRepo.findById(employeeId).orElse(null);
		if(employee != null) {
			employee.setJoinedTeam(teamRepo.findById(teamId).orElse(null));
			return employeeRepo.save(employee);
		}
		return null;
	}
	
	public Set<Employee> getEmployeeByCompany(Long companyId) {
		return employeeRepo.findByCompanyId(companyId);
	}

}
