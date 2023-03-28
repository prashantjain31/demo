package com.example.demo.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="team")
@JsonIdentityInfo(
	    generator = ObjectIdGenerators.PropertyGenerator.class, 
	    property = "id")
public class Team implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String teamName;
	
	@ManyToMany(mappedBy = "joinedTeams", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JsonBackReference
	Set<Employee> joins;
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;
	
	public Team() {
		
	}
	public Team(Long id, String teamName, Set<Employee> joins, Company company) {
		this.id = id;
		this.teamName = teamName;
		this.joins = joins;
		this.company = company;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public Set<Long> getJoins() {
		Set<Long> employeeIds = new HashSet<Long>();
		for(Employee employee : joins) {
			employeeIds.add(employee.getId());
		}
		return employeeIds;
//		return joins;
	}
	public void setJoins(Set<Employee> joins) {
		this.joins = joins;
	}
	public void setJoin(Employee employee) {
		if(this.joins == null) {
			this.joins = new HashSet<Employee>();
		}
		this.joins.add(employee);
	}
	public Long getCompany() {
		if(company == null) {
			return null;
		}
		return company.getId();
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	@Override
	public String toString() {
		return String.valueOf(id);
	}
}
