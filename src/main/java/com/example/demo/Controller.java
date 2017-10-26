package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

@RestController
public class Controller {

	@Autowired
	EmployeeRepository repo;

	@GetMapping(path = "/cache1")
	String tryOut1() {
		StopWatch sw = new StopWatch("Get employee for id from repo and cache");
		sw.start("Get employee for id 1 from repo");
		Employee emp = repo.findOne(1L);
		System.out.println("The id 1 from the repo is " + emp.toString());
		sw.stop();
		sw.start("Get employee for id 1 again?");
		Employee emp2 = repo.findOne(1L);
		sw.stop();

		System.out.println("Inserting another employee");
		repo.save(new Employee("Dan", "Mikusa", "Jr"));
		sw.start("Retreiving the inserted employee");
		Employee empl = repo.findOne(5L);
		System.out.println("The inserted employee is " + empl);
		sw.stop();

		sw.start("Get employee for id 2");
		Employee emp3 = repo.findOne(2L);
		System.out.println("The employee 2 in the repo is " + emp3);
		sw.stop();
		System.out.println("Deleting employee 2");
		repo.delete(2L);
		sw.start("Getting a deleted employee");
		Employee empl3 = repo.findOne(2L);
		System.out.println("The employee 2 still in the cache is " + empl3);
		sw.stop();
		System.out.println("**********************");

		System.out.println(sw.prettyPrint());
		System.out.println("**********End of cache 1**************");
		return "Done";
	}

	@GetMapping(path = "/cache2")
	String tryOut2() {
		StopWatch sw = new StopWatch("Get inserted employee");
		System.out.println("Inserting another employee");
		repo.save(new Employee("Timani", "T", "Jr"));
		sw.start("Retreiving the inserted employee");
		Employee empl = repo.findOne(5L);
		System.out.println("The inserted employee is " + empl);
		sw.stop();

		sw.start("Get employee for id 2");
		Employee emp3 = repo.findOne(2L);
		System.out.println("The employee 2 in the repo is " + emp3);
		sw.stop();
		System.out.println("Deleting employee 2");
		repo.delete(2L);
		sw.start("Getting a deleted employee");
		Employee empl3 = repo.findOne(2L);
		System.out.println("The employee 2 still in the cache is " + empl3);
		sw.stop();
		System.out.println("**********************");

		System.out.println(sw.prettyPrint());
		System.out.println("**********End of cache 2**************");
		return "Done";

	}
	
	
	@GetMapping(path = "/cache4")
	String tryOut4() {
		StopWatch sw = new StopWatch("Get Deleted  employee fom another cache");
		System.out.println("Getting the employee 4 by Id and then by last name");
		System.out.println("The employee 4 TESTSwa is " + repo.findOne(4L));
		sw.start("Getting employee by last name");
		System.out.println("The employee by last name Samson is " + repo.findByLastName("Samson"));
		sw.stop();
		System.out.println("Deleting from the byId cache");
		sw.start("Checking the byId cache for empoyee 4");
		
		repo.delete(4L);		
		//repo.deleteByLastName("Samson");
		System.out.println("The employee 4 after deletion from the byId cache is " + repo.findOne(4L));
		sw.stop();
		
		

		/*sw.start("Getting employee by last name");
		This still fetches the last name record
		System.out.println("The employee by last name Samson after calling delete is " + repo.findByLastName("Samson"));
		sw.stop();*/
		sw.start("Getting employee by last name again 2222");
		repo.resetAllEntries();
		System.out.println("The employee by last name Samson after calling reset is " + repo.findByLastName("Samson"));
		System.out.println(sw.prettyPrint());
	    return "Done";
	}

}
