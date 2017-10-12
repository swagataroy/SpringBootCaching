package com.example.demo;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class SpringJpaCacheAltApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaCacheAltApplication.class, args);
	}

	@Bean
	InitializingBean saveData(final EmployeeRepository repo) {
		return new InitializingBean() {
			public void afterPropertiesSet() throws Exception {
				repo.save(new Employee("Gita", "Subramaniam", "Jr"));
				repo.save(new Employee("Krishna", "Subramaniam", "Jr"));
				repo.save(new Employee("Krishna", "Yadava", "Sr"));
				repo.save(new Employee("Bhavani", "Lalitha", "Sr"));
			}

		};
	}

}
