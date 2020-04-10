package com.squirrel.pet;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BagOtype extends CrudRepository <ptype, Long> {

	default Long findTidByNameBreed(String specie, String breed) {
		Optional<ptype> Checker = this.findByNameBreed(specie, breed);
		ptype output;
		if(Checker.isPresent())
			output = Checker.get();
		else {
			output = new ptype();
			output.setName(specie);
			output.setBreed(breed);
			output = this.save(output);
		}
		return output.getID();
		
	}
	
	
	@Query(value = "select top 1 * from ptype where [name] = :specie and [breed] = :breed", nativeQuery = true)
	Optional<ptype> findByNameBreed(@Param("specie") String specie, @Param("breed") String breed) ;
	
	List <ptype> findByName(String name);
	
	List <ptype> findByBreed(String breed);
	
	List <ptype> findByNameOrBreed(String name, String breed);
	
	List <ptype> findByNameAndBreed(String name, String breed);
	
}
