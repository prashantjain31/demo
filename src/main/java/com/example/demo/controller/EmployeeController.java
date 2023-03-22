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

import com.example.demo.model.Employee;
import com.example.demo.model.LongRequest;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("employees")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/")
	public List<Employee> getEmployees() {
		return employeeService.getEmployees();
	}
	@PostMapping("/")
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeService.addEmployee(employee);
	}
	@DeleteMapping("/")
	public void deleteEmployees() {
		employeeService.deleteEmployees();
	}
	
	@GetMapping("/{employeeId}")
	public Employee getEmployee(@PathVariable("employeeId") Long employeeId) {
		if(employeeId != null) {
			return employeeService.getEmployee(employeeId);
		}
		return null;
	}
	@PutMapping("/{employeeId}")
	public Employee updateEmployee(@PathVariable("employeeId") Long employeeId, @RequestBody Employee employee) {
		if(employeeId != null) {
			return employeeService.updateEmployee(employeeId, employee);
		}
		return null;
	}
	@DeleteMapping("/{employeeId}")
	public void deleteEmployee(@PathVariable("employeeId") Long employeeId) {
		if(employeeId != null) {
			employeeService.deleteEmployee(employeeId);
		}
	}
	
	@PostMapping("/{employeeId}/addCompany")
	public Employee addCompany(@PathVariable("employeeId") Long employeeId, @RequestBody LongRequest companyId) {
		if(employeeId != null) {
			return employeeService.addCompany(employeeId, companyId.getLongValue());
		}
		return null;
	}
	
	@PostMapping("/{employeeId}/addTeam")
	public Employee addTeam(@PathVariable("employeeId") Long employeeId, @RequestBody LongRequest teamId) {
		if(employeeId != null) {
			return employeeService.addTeam(employeeId, teamId.getLongValue());
		}
		return null;
	}
}
