package com.squirrel.pet;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BagOpet extends CrudRepository <pet, Long>  {
	
	List <pet> findByName(String name);
	
	@Query(value = "select P.* from pet P left join ptype pt on tid = id where [breed] like :breed", nativeQuery = true)
	List <pet> findLikeBreed(String breed);

}
