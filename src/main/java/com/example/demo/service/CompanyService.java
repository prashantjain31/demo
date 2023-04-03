package com.example.demo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.helpers.ResourceNotFoundExceptionHandler;
import com.example.demo.manager.CompanyManager;
import com.example.demo.model.Company;
import com.example.demo.model.Employee;
import com.example.demo.repo.CompanyRepo;
import com.example.demo.repo.EmployeeRepo;

@Service
public class CompanyService {

	@Autowired
	CompanyRepo companyRepo;
	@Autowired
	CompanyManager companyManager;
	

	public List<Company> getCompanies() {
		return companyRepo.findAll();
	}
	@Cacheable("companies")
	public Company addCompany(Company company) {
		return companyRepo.save(company);
	}
	public void deleteCompanies() {
		companyRepo.deleteAll();
	}

	@Cacheable("companies")
	public Company getCompany(Long companyId) {
		if(companyId != null) {
			return companyRepo.findById(companyId).orElseThrow(() -> new ResourceNotFoundExceptionHandler("Company with id: " + companyId + " not found"));
		}
		return null;
	}
	@CachePut(value="companies", key="#companyId")
	public Company updateCompany(Long companyId, Company company) {
		if(companyId != null) {
			Company oldCompany = companyRepo.findById(companyId).orElseThrow(() -> new ResourceNotFoundExceptionHandler("Company with id: " + companyId + " not found"));
			if(oldCompany != null) {
				oldCompany.setCompanyName(company.getCompanyName());
//				oldCompany.setEmployees(company.getEmployees());
				return companyRepo.save(oldCompany);
			}
		}
		return null;
	}
	@CacheEvict("companies")
	public void deleteCompany(Long companyId) {
		if(companyId != null) {
			companyRepo.deleteById(companyId);
		}
	}

	@CachePut(value="companies", key="#companyId")
	public Company addEmployee(Long companyId, Long employeeId) {
		return companyManager.addEmployee(companyId, employeeId);
	}
	
}
