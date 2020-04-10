package com.squirrel.pet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetShopService  {
	
	@Autowired
	BagOowners roster;
	
	@Autowired
	BagOpet petList;
	
	@Autowired
	BagOtype knownTypes;
//POST--CREATE
	protected pet creatingPet(String name, String color, int age, String specie, String breed, 
							 String OwnerName, int OwnerAge) {
		
		pet newEntry = new pet();
		newEntry.setAge(age);
		newEntry.setColor(color);
		newEntry.setName(name);
		Long tid = knownTypes.findTidByNameBreed( specie,  breed);
		newEntry.setTid(tid);
		Long oid = roster.findOidByNameAge(OwnerName, OwnerAge);
		newEntry.setOid(oid);
		
		
		return petList.save(newEntry);
	}
	
	public owner creatingOwner(String OwnerName, int OwnerAge) {
		owner newO; 
		List<owner> tester = roster.findByNameAndAge(OwnerName, OwnerAge);
		if (!tester.isEmpty())
			return tester.remove(0);
		else {
			newO = new owner();
			newO.setAge(OwnerAge);
			newO.setName(OwnerName);
			newO = roster.save(newO);
		}
		return newO;
	}
	
	public ptype creatingType(String specie, String breed) {
		ptype newPT;
		List<ptype> tester = knownTypes.findByNameAndBreed(specie, breed);
		
		if(!tester.isEmpty())
			return tester.remove(0);
		
		else {
			newPT = new ptype(specie, breed);
			newPT = knownTypes.save(newPT);
		}
		
		return newPT;
	}
//GET---Read	
	public List<pet> lookupDogs(){
		return petList.findByName("dog");
	}

	public List<pet> breadLike(String breed){
		breed = "%"+breed+"%";
		return petList.findLikeBreed(breed);
	}
	
	public Iterable<ptype> listTypes(){
		return knownTypes.findAll();
	}
	
	public Iterable<owner> listOwners(){
		return roster.findAll();	
	}
//PUT---UPDATE
	public boolean updatePetBreed(String Type, String Breed, Long PID) {
		pet upDate = petList.findById(PID).isPresent()?petList.findById(PID).get():null;
		if (upDate==null)
			return false;
		Long tid = knownTypes.findTidByNameBreed( Type,  Breed);
		upDate.setTid(tid);
		petList.save(upDate);
		return true;
	}

	public boolean updatePetOwner(String Name, int age, Long PID) {
		pet upDate = petList.findById(PID).isPresent()?petList.findById(PID).get():null;
		if (upDate==null)
			return false;
		Long oid = roster.findOidByNameAge(Name, age);
		upDate.setOid(oid);
		petList.save(upDate);
		return true;
	}
//DELETE
	public boolean deletePets (long id) {
		if(petList.existsById(id))
			petList.deleteById(id);
		else
			return false;
		
		return true;
	}
	
}
