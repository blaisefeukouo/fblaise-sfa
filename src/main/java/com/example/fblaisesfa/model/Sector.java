package com.example.fblaisesfa.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sector")
public class Sector extends SfaDomain{

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@OneToMany(mappedBy = "sector")
	private List<Client> clients;

	public Sector() {
	}

	public Sector(String name, String description) {
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

	public List<Client> getClients() {
		return clients;
	}

	public void copyValuesFrom(Sector sector) {
		this.name = sector.name;
		this.description = sector.description;
	}

}
