package br.com.rafael.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.rafael.data.vo.v1.PersonVO;
import br.com.rafael.services.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {	
	
	@Autowired
	private PersonService service;

	@GetMapping(value = "/{id}",			
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonVO> findById(@PathVariable(value = "id") Long id) throws Exception {		
		
		PersonVO person =  service.findById(id);
		return ResponseEntity.ok(person);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PersonVO>> findAll() {
		List<PersonVO> persons = service.findAll();
		return ResponseEntity.ok(persons);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonVO> create(@RequestBody PersonVO person) {
		PersonVO personSalva = service.create(person);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(personSalva.getId()).toUri(); 
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonVO> update(@RequestBody PersonVO person, @PathVariable("id") Long id) {
		
		try {
			PersonVO personSalvo = service.update(person);
			return ResponseEntity.status(HttpStatus.OK).body(personSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	

}
