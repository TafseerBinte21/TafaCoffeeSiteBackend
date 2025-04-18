package com.tafa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coffeelists")
public class Coffee {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String origin;

    private double price;

    @Column(name = "imageurl")
    private String imageUrl; // This will store image path from frontend assets
    
    public Coffee() {
    	
    }

	public Coffee(String name, String description, String origin, double price, String imageUrl) {
		super();
		this.name = name;
		this.description = description;
		this.origin = origin;
		this.price = price;
		this.imageUrl = imageUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "Coffee [id=" + id + ", name=" + name + ", description=" + description + ", origin=" + origin
				+ ", price=" + price + ", imageUrl=" + imageUrl + "]";
	}
    
    

}
