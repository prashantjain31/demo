package com.example.demo.manager;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.helpers.ResourceNotFoundExceptionHandler;
import com.example.demo.model.Company;
import com.example.demo.model.Employee;
import com.example.demo.repo.CompanyRepo;
import com.example.demo.repo.EmployeeRepo;

import jakarta.transaction.Transactional;

@Service
public class CompanyManager {
	@Autowired
	CompanyRepo companyRepo;
	@Autowired
	EmployeeRepo employeeRepo;
	
	@Transactional
	public Company addEmployee(Long companyId, Long employeeId) {
		Company company = companyRepo.findById(companyId)
				.orElseThrow(() -> new ResourceNotFoundExceptionHandler("Company with id: " + companyId + " not found"));
		if(company != null) {
			Employee employee = employeeRepo.findById(employeeId)
					.orElseThrow(() -> new ResourceNotFoundExceptionHandler("Employee with id: " + employeeId + " not found"));
			if(employee == null) {
				return company;
			}
			if(company.getEmployees() == null) {
				company.setEmployees(new HashSet<Employee>());
			}
			employee.setCompany(company);
			company.setEmployee(employee);
			return companyRepo.save(company);
		}
		return null;
	}
}
