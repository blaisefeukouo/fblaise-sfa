package com.example.fblaisesfa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product extends SfaDomain {

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "price")
	private float price;
	@ManyToOne()
	@JoinColumn(name = "family_id", nullable = false)
	private ProductFamily family;

	public Product() {
	}

	public Product(String name, String description, float price, ProductFamily family) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.family = family;
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public ProductFamily getFamily() {
		return family;
	}

	public void setFamily(ProductFamily family) {
		this.family = family;
	}

	public void copyValuesFrom(Product product) {
		this.name = product.name;
		this.description = product.description;
		this.price = product.price;
		this.family = product.family;
	}

}
