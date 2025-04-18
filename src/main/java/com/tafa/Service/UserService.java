package com.tafa.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tafa.Repository.UsersRepository;
import com.tafa.entity.User;




@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	@Lazy
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UsersRepository userRepository;
	
	@Autowired
    private EntityManager entityManager;
	
	@Transactional
	public User save(User entity) {
		return userRepository.save(entity);
	}
	
	public User registerUser(User user) {
        // Check if email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email is already in use!");
           
        }
       
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}

        
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
        		Collections.emptyList());
	}
	

	
	public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
	
	public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
	
	public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
	



	
}
