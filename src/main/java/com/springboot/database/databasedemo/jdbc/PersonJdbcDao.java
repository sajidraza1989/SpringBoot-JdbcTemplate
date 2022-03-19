package com.springboot.database.databasedemo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springboot.database.databasedemo.entity.Person;

@Repository
public class PersonJdbcDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	class PersonRowMapper implements RowMapper<Person>{

		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person person = new Person();
			person.setId(rs.getInt("id"));
			person.setName(rs.getString("name"));
			person.setLocation(rs.getString("location"));
			person.setBirthDate(rs.getDate("birth_date"));
			return person;
		}

	}

    /*public List<Person> findAll(){
    	
    	return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper(Person.class));
    }*/
	
	public List<Person> findAll(){
    	
    	return jdbcTemplate.query("select * from person", new PersonRowMapper());
    	
    }
	
	public int insert(Person p){
    	
    	return jdbcTemplate.update("insert into person(id, name, location, birth_date) values(?, ?, ?, ?)", 
    						new Object[]{p.getId(), p.getName(), p.getLocation(), new Timestamp(p.getBirthDate().getTime())});
    }
	
	public int update(Person p){
    	
    	return jdbcTemplate.update("update person set name = ? where id= ?", 
    						new Object[]{p.getName(), p.getId()});
    }
	
	public int delete(int id) {
		return jdbcTemplate.update("delete from person where id = ?", new Object[] {id});
	}

}
