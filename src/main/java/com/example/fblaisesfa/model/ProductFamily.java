package com.example.fblaisesfa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "productfamily")
public class ProductFamily extends SfaDomain {

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	public ProductFamily() {
	}

	public ProductFamily(String name, String description) {
		super();
		this.name = name;
		this.description = description;
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

	public void copyValuesFrom(ProductFamily familyWithNewValues) {
		this.name = familyWithNewValues.name;
		this.description = familyWithNewValues.description;
	}

}
