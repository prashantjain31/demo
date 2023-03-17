package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;

@RestController
public class PersonController {
	
	@Autowired
	PersonService personService;
	
//	@GetMapping("pers")
//	public List<Person> getAllPersons() {
////		while using this approach change return type to ResponseEntity<List<Person>>
////		return new ResponseEntity<>(personService.getAllPersons(), HttpStatus.OK);
//		return personService.getAllPersons();
//	}
	
	@GetMapping("getone/{id}") 
	public Person getOne(@PathVariable("id") Long id) {
		return personService.getOnePerson(id);
	}
	
	@PostMapping("addPerson")
	public void addPerson(@RequestBody Person person) {
		personService.addSinglePerson(person);
	}
	
}
