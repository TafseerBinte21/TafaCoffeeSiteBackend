package com.tafa.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tafa.Repository.CartRepository;
import com.tafa.Repository.CoffeeRepository;
import com.tafa.Repository.UsersRepository;
import com.tafa.entity.Cart;
import com.tafa.entity.Coffee;
import com.tafa.entity.User;

@Service
public class CartService {
	
	
	@Autowired
    private CartRepository cartItemRepo;

    @Autowired
    private CoffeeRepository coffeeRepo;

    @Autowired
    private UsersRepository userRepo;
    
    
    
    public void addToCart(String username, Long coffeeId, int quantity) {
        User user = userRepo.findByUsername(username);
                      
        Coffee coffee = coffeeRepo.findById(coffeeId)
                      .orElseThrow(() -> new RuntimeException("Coffee not found"));

        Cart existingCartItem = cartItemRepo.findByUserAndCoffee(user, coffee);

        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
        } else {
            existingCartItem = new Cart(user, coffee, quantity);
        }

        cartItemRepo.save(existingCartItem);
    }


    public List<Cart> getCartItems(String username) {
        User user = userRepo.findByUsername(username);
        return cartItemRepo.findByUser(user);
    }
    
    public void removeFromCart(String username, Long coffeeId) {
        User user = userRepo.findByUsername(username);
        Coffee coffee = coffeeRepo.findById(coffeeId).orElse(null);

        if (user != null && coffee != null) {
            Cart cart = cartItemRepo.findByUserAndCoffee(user, coffee);
            if (cart != null) {
            	cartItemRepo.delete(cart);
            }
        }
    }


}
