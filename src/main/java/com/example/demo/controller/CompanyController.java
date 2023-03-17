package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Company;
import com.example.demo.repo.CompanyRepo;

@RestController
public class CompanyController {
	
	@Autowired
	CompanyRepo repo;
	
	@PostMapping("addCompany")
	public void addCompany(@RequestBody Company company) {
		repo.save(company);
	}
	
}
