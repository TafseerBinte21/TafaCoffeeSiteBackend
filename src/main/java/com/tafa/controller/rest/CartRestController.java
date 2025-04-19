package com.tafa.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tafa.Service.CartService;
import com.tafa.entity.Cart;

@RestController
@RequestMapping("/rest/api/user")
public class CartRestController {
	
	
	 @Autowired
	 private CartService cartService;
	
	
	 @RequestMapping(value = "/add-to-cart", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	 public ResponseEntity<Map<String, Object>> addToCart(@RequestBody Map<String, Object> payload) {
	     Map<String, Object> response = new HashMap<>();
	     try {
	         String username = (String) payload.get("username");
	         Long coffeeId = Long.valueOf(payload.get("coffeeId").toString());
	         int quantity = payload.get("quantity") != null ? Integer.parseInt(payload.get("quantity").toString()) : 1;

	         cartService.addToCart(username, coffeeId, quantity);

	         response.put("msg", "Coffee added to cart");
	         response.put("status", HttpStatus.OK.value());
	         return new ResponseEntity<>(response, HttpStatus.OK);
	     } catch (Exception e) {
	         response.put("msg", "Failed to add coffee to cart");
	         response.put("error", e.getMessage());
	         response.put("status", HttpStatus.BAD_REQUEST.value());
	         return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	     }
	 }


	 @RequestMapping(value = "/get-cart", method = RequestMethod.GET, produces = "application/json")
	 public ResponseEntity<Map<String, Object>> getCart(@RequestParam String username) {
	     Map<String, Object> response = new HashMap<>();
	     try {
	         List<Cart> items = cartService.getCartItems(username);
	         // Adding quantity to the response
	         List<Map<String, Object>> cartDetails = items.stream().map(item -> {
	             Map<String, Object> cartItem = new HashMap<>();
	             cartItem.put("coffee", item.getCoffee()); // Assuming 'getCoffee()' gets the coffee details
	             cartItem.put("quantity", item.getQuantity()); // Assuming 'getQuantity()' gets the quantity
	             return cartItem;
	         }).collect(Collectors.toList());

	         response.put("msg", "Cart fetched successfully");
	         response.put("data", cartDetails);
	         response.put("status", HttpStatus.OK.value());
	         return new ResponseEntity<>(response, HttpStatus.OK);
	     } catch (Exception e) {
	         response.put("msg", "Failed to fetch cart");
	         response.put("error", e.getMessage());
	         response.put("status", HttpStatus.BAD_REQUEST.value());
	         return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	     }
	 }
	 
	 
	 @RequestMapping(value = "/remove-from-cart", method = RequestMethod.DELETE, produces = "application/json")
	 public ResponseEntity<Map<String, Object>> removeFromCart(@RequestParam String username, @RequestParam Long coffeeId) {
	     Map<String, Object> response = new HashMap<>();
	     try {
	         cartService.removeFromCart(username, coffeeId);
	         response.put("msg", "Item removed from cart successfully");
	         response.put("status", HttpStatus.OK.value());
	         return new ResponseEntity<>(response, HttpStatus.OK);
	     } catch (Exception e) {
	         response.put("msg", "Failed to remove item from cart");
	         response.put("error", e.getMessage());
	         response.put("status", HttpStatus.BAD_REQUEST.value());
	         return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	     }
	 }

	 @GetMapping(value = "/user-cart", produces = "application/json")
	 public ResponseEntity<Map<String, Object>> getUserCart(@RequestParam String username) {
	     List<Cart> cartItems = cartService.getCartItemsByUsername(username);
	     Map<String, Object> response = new HashMap<>();
	     response.put("status", "success");
	     response.put("data", cartItems);
	     return ResponseEntity.ok(response);
	 }


}
