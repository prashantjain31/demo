package com.example.demo.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="company")
@JsonIdentityInfo(
	    generator = ObjectIdGenerators.PropertyGenerator.class, 
	    property = "id")
public class Company implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String companyName;
	
//	@JsonBackReference
//	@JsonIgnore
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Employee> employees;
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Team> teams;
	
	public Company() {
		
	}
	public Company(Long id, String companyName, Set<Employee> employees, Set<Team> teams) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.employees = employees;
		this.teams = teams;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Set<Long> getEmployees() {
		Set<Long> employeeIds = new HashSet<Long>();
		for(Employee employee : employees) {
			employeeIds.add(employee.getId());
		}
		return employeeIds;
//		return employees;
	}
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	public void setEmployee(Employee employee) {
		if(this.employees == null) {
			this.employees = new HashSet<Employee>();
		}
		this.employees.add(employee);
	}
	public Set<Long> getTeams() {
		Set<Long> teamIds = new HashSet<Long>();
		for(Team team : teams) {
			teamIds.add(team.getId());
		}
		return teamIds;
//		return teams;
	}
	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}
	public void setTeam(Team team) {
		if(this.teams == null) {
			this.teams = new HashSet<Team>();
		}
		this.teams.add(team);
	}
	
	
	@Override
	public String toString() {
		return String.valueOf(id);
	}
	
}
