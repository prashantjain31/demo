package com.example.demo.model;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="team")
public class Team implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String teamName;
	
	@ManyToMany(mappedBy = "joinedTeams")
	Set<Employee> joins;
	
	public Team() {
		
	}
	public Team(Long id, String teamName, Set<Employee> joins) {
		this.id = id;
		this.teamName = teamName;
		this.joins = joins;
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
	public Set<Employee> getJoins() {
		return joins;
	}
	public void setJoins(Set<Employee> joins) {
		this.joins = joins;
	}
	
}
