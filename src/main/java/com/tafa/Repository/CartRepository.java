package com.tafa.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tafa.entity.Cart;
import com.tafa.entity.Coffee;
import com.tafa.entity.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
	
	
    List<Cart> findByUser(User user);
    
    Cart findByUserAndCoffee(User user, Coffee coffee);
    
    @Modifying
    @Transactional
    @Query("DELETE FROM Cart c WHERE c.user.username = :username AND c.coffee.id = :coffeeId")
    void deleteByUsernameAndCoffeeId(@Param("username") String username, @Param("coffeeId") Long coffeeId);



}
