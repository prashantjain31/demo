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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="employee")
@JsonIdentityInfo(
	    generator = ObjectIdGenerators.PropertyGenerator.class, 
	    property = "id")
public class Employee implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private Role role;
	
	@ManyToOne
	@JoinColumn(name = "company_id")
//	@JsonManagedReference
	private Company company;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name="employee_team",
			joinColumns = @JoinColumn(name="employee_id"),
			inverseJoinColumns = @JoinColumn(name="team_id"))
//	@JsonManagedReference
	Set<Team> joinedTeams;

	public Employee() {
		super();
	}
	public Employee(Long id, String name, Company company, Set<Team> joinedTeams, Role role) {
		super();
		this.id = id;
		this.name = name;
		this.company = company;
		this.joinedTeams = joinedTeams;
		this.role = role;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Set<Team> getJoinedTeams() {
		return joinedTeams;
	}
	public void setJoinedTeams(Set<Team> joinedTeams) {
		this.joinedTeams = joinedTeams;
	}
	public void setJoinedTeam(Team team) {
		if(this.joinedTeams == null) {
			this.joinedTeams = new HashSet<Team>();
		}
		this.joinedTeams.add(team);
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return String.valueOf(id);
	}
	
}
