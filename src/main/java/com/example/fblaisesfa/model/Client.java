package com.example.fblaisesfa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class Client extends SfaDomain {

	

	@Column(name = "COMMERCIAL_NAME")
	private String commercialName;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "EMAIL")
	private String email;

	@ManyToOne()
	@JoinColumn(name = "SECTOR_ID", nullable = false)
	private Sector sector;

	@OneToMany(mappedBy = "client")
	private List<Command> commands=new ArrayList<>();

	public Client() {
	}

	public Client(String commercialName, String firstName, String lastName, String email, Sector sector) {
		super();
		this.commercialName = commercialName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.sector = sector;
	}

	

	public String getCommercialName() {
		return commercialName;
	}

	public void setCommercialName(String commercialName) {
		this.commercialName = commercialName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public void copyValuesFrom(Client client) {
		this.commercialName = client.commercialName;
		this.firstName = client.firstName;
		this.lastName = client.lastName;
		this.email = client.email;
		this.sector = client.sector;
	}

}
