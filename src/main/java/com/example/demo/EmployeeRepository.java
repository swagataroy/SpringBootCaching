package com.example.demo;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {
	
	@CachePut(value="byId", key = "#result.id")
	<S extends Employee> S save(S entity);
	
	
	//@Caching(evict ={@CacheEvict("byId"),@CacheEvict(value="byLastName",key="#p0")})
	@CacheEvict("byId")
	void delete(Long id);

	@Cacheable(cacheNames ="byLastName",unless="#result == null")
	Employee findByLastName(String lastName);
	
	@Cacheable(value= "byId", unless="#result == null")
	Employee findOne(Long id);

}
