package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Person;
import com.example.demo.repo.PersonRepo;

@Service
public class PersonService {
	@Autowired
	PersonRepo personRepo;
	
//	@Cacheable(value="persons")
//	public List<Person> getAllPersons() {
//		List<Person> person = personRepo.findAll();
//		System.out.println("running get from db");
//		return person;
//	}
	@Cacheable(value="persons")
	public Person getOnePerson(Long id) {
		return personRepo.findById(id).get();
	}
	
	public void addSinglePerson(Person person) {
		personRepo.save(person);
	}
}
