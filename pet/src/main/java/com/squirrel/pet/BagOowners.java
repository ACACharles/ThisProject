package com.squirrel.pet;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BagOowners extends CrudRepository <owner, Long> {

	default Long findOidByNameAge(String ownerName, int ownerAge) {
		Optional<owner> Checker = this.findByNameAge(ownerName, ownerAge);
		owner output;
		if(Checker.isPresent())
			output = Checker.get();
		else {
			output = new owner();
			output.setName(ownerName);
			output.setAge(ownerAge);
			output = this.save(output);
		}
		return output.getID();
		
	}
	
	
	@Query(value = "select top 1 * from owner where [name] = :name and [age] = :age", nativeQuery = true)
	Optional<owner> findByNameAge(@Param("name") String specie, @Param("age") int age) ;
	
	List <owner> findByName(String name);
	
	List <owner> findByAge(int Age);
	
	List <owner> findByNameOrAge(String name, int Age);
	
	List <owner> findByNameAndAge(String name, int Age);


}
