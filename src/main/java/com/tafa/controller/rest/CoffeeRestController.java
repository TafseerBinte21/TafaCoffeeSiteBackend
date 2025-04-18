package com.tafa.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tafa.Service.CoffeeService;
import com.tafa.entity.Coffee;

@RestController
@RequestMapping("/rest/api/user")
public class CoffeeRestController {
	
	@Autowired
    private CoffeeService coffeeService;
	
	
	 @RequestMapping(value = "/get-all-coffees", method = RequestMethod.GET, produces = "application/json")
	    public ResponseEntity<Map<String, Object>> getAllCoffees() {
	        Map<String, Object> response = new HashMap<>();
	        try {
	            List<Coffee> coffeeList = coffeeService.getAllCoffees();
	            response.put("msg", "Coffee list fetched successfully");
	            response.put("data", coffeeList);
	            response.put("status", HttpStatus.OK.value());
	            return new ResponseEntity<>(response, HttpStatus.OK);
	        } catch (Exception e) {
	            response.put("msg", "Error fetching coffee list");
	            response.put("error", e.getMessage());
	            response.put("status", HttpStatus.BAD_REQUEST.value());
	            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	        }
	    }
	 
	 
	 @RequestMapping(value = "/add-coffees", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	 public ResponseEntity<Map<String, Object>> addCoffees(@RequestBody List<Coffee> coffees) {
	     Map<String, Object> response = new HashMap<>();
	     try {
	         List<Coffee> savedCoffees = coffeeService.saveAllCoffees(coffees);
	         response.put("msg", "Coffees saved successfully");
	         response.put("data", savedCoffees);
	         response.put("status", HttpStatus.CREATED.value());
	         return new ResponseEntity<>(response, HttpStatus.CREATED);
	     } catch (Exception e) {
	         response.put("msg", "Error saving coffees");
	         response.put("error", e.getMessage());
	         response.put("status", HttpStatus.BAD_REQUEST.value());
	         return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	     }
	 }


}
