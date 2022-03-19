package com.springboot.database.databasedemo;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springboot.database.databasedemo.entity.Person;
import com.springboot.database.databasedemo.jdbc.PersonJdbcDao;

@SpringBootApplication
public class DatabaseDemoApplication implements CommandLineRunner {
	
	@Autowired
	PersonJdbcDao personJdbcDao;
	
	Logger logger = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {
		SpringApplication.run(DatabaseDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		logger.info("All person {}",  personJdbcDao.findAll());
		
		logger.info("Number of person added to database -  {} ",  personJdbcDao.insert(new Person(10004, "Santosh", "USA", new Date(1998, 10, 11))));
		
		logger.info("Update for id 10004 name Snatosh to Ashfaq -  {} ",  personJdbcDao.update(new Person(10004, "Ashfaq", "USA", new Date(1998, 10, 11))));
		
		//logger.info("Deleting 10003 person -  {} ",  personJdbcDao.delete(10003));
	}

}
