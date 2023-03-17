package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.repo.EmployeeRepo;
import com.example.demo.repo.PersonRepo;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeRepo repo;
	@Autowired
	PersonRepo personRepo;
	
	@PostMapping("addEmployee")
	public void addEmployee(@RequestBody Employee employee) {
		repo.save(employee);
	}
	
	@PutMapping("employee/addPerson")
	public void addPerson(@RequestParam(name = "id1") Long employeeId, @RequestParam(name = "id2") Long personId) {
		Employee emp = repo.findById(employeeId).get();
		emp.setPerson(personRepo.findById(personId).get());
		repo.save(emp);
	}
	// @PathVariable can be used to extract parameters from url (like we used to extract them in nodejs)
	
	@PostMapping("addEmployeeWithCourse")
	public void addEmployeeWithCourse(@RequestBody Employee employee) {
		repo.save(employee);
	}
}
