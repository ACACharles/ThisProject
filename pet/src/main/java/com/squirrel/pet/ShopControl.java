package com.squirrel.pet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopControl {
	
	@Autowired
	PetShopService tasks;
	
	@PostMapping("/add/pet")
	public boolean addPet(@RequestParam("name")String name, @RequestParam("color")String color,
			@RequestParam("age")int age, @RequestParam("specie")String specie, @RequestParam("breed")String breed, 
			@RequestParam("OwnerName")String OwnerName, @RequestParam("OwnerAge")int OwnerAge) {
		
		tasks.creatingPet(name, color, age, specie, breed, OwnerName, OwnerAge);
		
		return true;
		
	}
	
	@PostMapping("/add/owner/{on}/{oa}")
	public void addOwner(@PathVariable("on")String OwnerName, @PathVariable("oa")int OwnerAge) {
		tasks.creatingOwner(OwnerName, OwnerAge);
	}
	
	@PostMapping("/add/type")
	public void addType(@RequestParam("specie") String specie,@RequestParam("breed") String breed) {
		tasks.creatingType(specie, breed);
	}

	
	@GetMapping("/dog")
	public List<pet> findDogs() {
		return tasks.lookupDogs();
	}
	
	@GetMapping("/breed/like/{breed}")
	public List<pet> likeBreed(@PathVariable String breed) {
		return tasks.breadLike(breed);
	}
	
	@GetMapping("/list/type")
	public Iterable<ptype> allTypes() {
		return tasks.listTypes();
	}
	
	@GetMapping("/list/owner")
	public Iterable<owner> allOwners() {
		return tasks.listOwners();
	}
	
	@PutMapping("/update/Pet/breed")
	public boolean upBreed(@RequestParam("Type") String Type, @RequestParam("Breed") String Breed,@RequestParam("PID") Long PID) {
		return tasks.updatePetBreed(Type, Breed, PID);
		
	}

	@PutMapping("/update/Pet/owner")
	public boolean upOwner(@RequestParam("Name") String Name,@RequestParam("age") int age,@RequestParam("PID") Long PID) {
		return tasks.updatePetOwner(Name, age, PID);
	}
	
	@DeleteMapping("/dead/pet")
	public boolean burryPet(@RequestParam("id") Long ID) {
		return tasks.deletePets(ID);
	}
}
