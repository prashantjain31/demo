package com.example.demo.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.helpers.ResourceNotFoundExceptionHandler;
import com.example.demo.manager.EmployeeManager;
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
	EmployeeManager employeeManager;
	
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
		return employeeRepo.findById(employeeId).orElseThrow(() -> new ResourceNotFoundExceptionHandler("Employee with id: " + employeeId + " not found"));
	}
	@CachePut(value="employees", key="#employeeId")
	public Employee updateEmployee(Long employeeId, Employee employee) {
		Employee emp = employeeRepo.findById(employeeId).orElseThrow(() -> new ResourceNotFoundExceptionHandler("Employee with id: " + employeeId + " not found"));
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
		return employeeManager.addCompany(employeeId, companyId);
	}
	
	@CachePut(value="employees", key="#employeeId")
	public Employee addTeam(Long employeeId, Long teamId) {
		return employeeManager.addTeam(employeeId, teamId);
	}
	
	public Set<Employee> getEmployeeByCompany(Long companyId) {
		return employeeRepo.findByCompanyId(companyId);
	}

}
