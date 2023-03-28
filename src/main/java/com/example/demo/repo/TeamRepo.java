package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Team;

public interface TeamRepo extends JpaRepository<Team, Long> {
	
//	public List<Team> findAll();
//	
//	@Cacheable("teams")
//	public Optional<Team> findById(Long id);
//	
//	public void deleteAll();
//	
//	@CacheEvict("teams")
//	public void deleteById(Long id);
//	
//	@Cacheable("teams")
//	public Team save(Team team);
	
}
