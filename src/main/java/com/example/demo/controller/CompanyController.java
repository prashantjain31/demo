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

import com.example.demo.model.Company;
import com.example.demo.model.Employee;
import com.example.demo.model.LongRequest;
import com.example.demo.service.CompanyService;

@RestController
@RequestMapping("companies")
public class CompanyController {
	
	@Autowired
	CompanyService companyService;
	
	@GetMapping("/")
	public List<Company> getCompanies() {
		return companyService.getCompanies();
	}
	@PostMapping("/")
	public Company addCompany(@RequestBody Company company) {
		return companyService.addCompany(company);
	}
	@DeleteMapping("/")
	public void deleteCompanies() {
		companyService.deleteCompanies();
	}
	
	@GetMapping("/{companyId}")
	public Company getCompany(@PathVariable("companyId") Long companyId) {
		if(companyId != null) {
			return companyService.getCompany(companyId);
		}
		return null;
	}
	@PutMapping("/{companyId}")
	public Company updateCompany(@PathVariable("companyId") Long companyId, @RequestBody Company company) {
		if(companyId != null) {
			return companyService.updateCompany(companyId, company);
		}
		return null;
	}
	@DeleteMapping("/{companyId}")
	public void deleteCompany(@PathVariable("companyId") Long companyId) {
		if(companyId != null) {
			companyService.deleteCompany(companyId);
		}
	}
	
//	@PostMapping("/{companyId}/addEmployee")
//	public Company addEmployee(@PathVariable("companyId") Long companyId, @RequestBody LongRequest employeeId) {
//		if(employeeId != null) {
//			return companyService.addEmployee(companyId, employeeId.getLongValue());
//		}
//		return null;
//	}
}
