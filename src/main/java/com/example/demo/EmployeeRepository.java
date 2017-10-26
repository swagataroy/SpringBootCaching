package com.example.demo;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	@CachePut(value = "byId", key = "#result.id")
	<S extends Employee> S save(S entity);

	// @Caching(evict
	// ={@CacheEvict("byId"),@CacheEvict(value="byLastName",key="#p0")})
	 //@CacheEvict("byId")
	//Trying to delete both byId and byLastName cache
	@Caching(evict ={@CacheEvict("byId"),@CacheEvict(cacheNames =  "byLastName")})
	void delete(Long id);

	@Cacheable(cacheNames = "byLastName", unless = "#result == null")
	Employee findByLastName(String lastName);

	@Cacheable(value = "byId", unless = "#result == null")
	Employee findOne(Long id);

	// @CacheEvict("byId"),@CacheEvict(value="byLastName",key="#p0"))
	// @Caching(evict
	// ={@CacheEvict("byId"),@CacheEvict(value="byLastName",key="#p0")})
	// @Caching(evict ={@CacheEvict("byId"),@CacheEvict(cacheNames =
	// "byLastName")})
	// @CacheEvict("byId")
	@Caching(evict = { @CacheEvict("byId"), @CacheEvict(value = "employee", key = "#LastName") })
	void deleteByLastName(String name);

	//@CacheEvict("byId")
	// @Caching(evict ={@CacheEvict("byLastName"
	//@CacheEvict(value="byLastName", allEntries=true)
	//void deleteByLastNameAgain(Long id);
	
	//reset by LastName	
	//@CacheEvict(value = "byLastName", allEntries = true)
	@CacheEvict(value = { "byId", "byLastName" }, allEntries = true)
	  public default void resetAllEntries() {
	    // Intentionally blank
	  }
	
	

}
