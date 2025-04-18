package com.tafa.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tafa.Repository.CoffeeRepository;
import com.tafa.entity.Coffee;

@Service
public class CoffeeService {
	
	
	 @Autowired
	    private CoffeeRepository coffeeRepository;

	    public List<Coffee> getAllCoffees() {
	        return coffeeRepository.findAll();
	    }
	    
	    
	    public List<Coffee> saveAllCoffees(List<Coffee> coffees) {
	        return coffeeRepository.saveAll(coffees);
	    }


}
