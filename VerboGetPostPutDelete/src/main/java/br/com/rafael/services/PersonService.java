package br.com.rafael.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.rafael.model.Person;

@Service
public class PersonService {
	
	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	
	public Person findById(String id) {
		
		logger.info("Finding one person!");
		
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Rafael");
		person.setLastName("Souza");
		person.setAddress("Fortaleza - Ce");
		person.setGender("Male");
		
		return person;
	}
	
	public List<Person> findAll() {
		logger.info("Finding all person!");
		
		Person person1 = new Person(counter.incrementAndGet(), "Luiz", "Fernandes", "13 de maio", "Male");
		Person person2 = new Person(counter.incrementAndGet(), "Maria", "Luiza", "Centro", "Female");
		Person person3 = new Person(counter.incrementAndGet(), "Yuky", "Asuna", "Tokio Japão", "Female");
		Person person4 = new Person(counter.incrementAndGet(), "Rafael", "Souza", "Fortaleza -ce", "Male");
		
		List<Person> persons = new ArrayList<>();
		persons.addAll(Arrays.asList(person1, person2, person3, person4));
		
		return persons;
	}
	
	public Person create(Person person) {
		logger.info("Creating one person!");
		
		return person;
	}
	
	public Person update(Person person) {
		logger.info("Update one person!");
		
		return person;
	}
	
	public void delete(String id) {
		logger.info("Deleting one person!");
	}

}
