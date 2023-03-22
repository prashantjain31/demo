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
import jakarta.persistence.ManyToMany;
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
	
	@ManyToMany(mappedBy = "joinedTeams", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JsonBackReference
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
	public void setJoin(Employee employee) {
		if(this.joins == null) {
			this.joins = new HashSet<Employee>();
		}
		this.joins.add(employee);
	}
	
	@Override
	public String toString() {
		return String.valueOf(id);
	}
}
