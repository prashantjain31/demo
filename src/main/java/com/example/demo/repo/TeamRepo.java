package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Team;

public interface TeamRepo extends JpaRepository<Team, Long> {

}
