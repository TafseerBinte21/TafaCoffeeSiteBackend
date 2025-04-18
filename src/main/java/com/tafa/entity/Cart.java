package com.tafa.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	


	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
     private User user;

	@ManyToOne(optional = false)
	@JoinColumn(name = "coffeeId")
	private Coffee coffee;

	private int quantity;
	
	public Cart() {
		
	}

	public Cart(User user, Coffee coffee, int quantity) {
		super();
		this.user = user;
		this.coffee = coffee;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Coffee getCoffee() {
		return coffee;
	}

	public void setCoffee(Coffee coffee) {
		this.coffee = coffee;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", user=" + user + ", coffee=" + coffee + ", quantity=" + quantity + "]";
	}
	
	

}
