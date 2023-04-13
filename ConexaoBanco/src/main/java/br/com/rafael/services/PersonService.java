package br.com.rafael.services;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rafael.controller.exception.ResourceNotFoundException;
import br.com.rafael.data.vo.v1.PersonVO;
import br.com.rafael.mapper.DozerMapper;
import br.com.rafael.model.Person;
import br.com.rafael.repositories.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository repository;
	
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	
	public PersonVO findById(Long id) {	
		logger.info("Find one person!");
		var entity = repository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("No records found for this ID! " + id));		
		
		return DozerMapper.parseObject(entity, PersonVO.class);
	}
	
	public List<PersonVO> findAll() {
		logger.info("FindAll person!");
		
		return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class) ;
	}
	
	public PersonVO create(PersonVO person) {
		logger.info("Creating one person!");		
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);		
		return vo;
	}
	
	public PersonVO update(PersonVO person) {
		logger.info("Update one person!");
		var entity = repository.findById(person.getId()).
				orElseThrow(() -> new ResourceNotFoundException("No records found for this ID! " + person.getId()));
		
		BeanUtils.copyProperties(person, entity, "id");
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		
		
		return vo;
	}
	
	public void delete(Long id) {
		logger.info("Deleting one person!");
		findById(id);
		var entity = repository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("No records found for this ID! " + id));
		repository.delete(entity);
	}

}
