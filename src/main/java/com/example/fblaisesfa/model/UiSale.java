package com.example.fblaisesfa.model;

import java.util.Date;

public class UiSale {

	private User user;
	private Date date;
	private Client client;
	private Sale sale;

	public UiSale() {
	}

	public UiSale(User user, Sale sale, Client client, Date date) {
		super();
		this.user = user;
		this.date = date;
		this.client = client;
		this.sale = sale;
	}

	public UiSale(User user, Sale sale) {
		super();
		this.user = user;
		this.sale = sale;
	}

	public User getUser() {
		return user;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Long getClientId() {
		if (client == null) {
			return null;
		}
		return client.getId();
	}

	public Long getSaleId() {
		if (sale == null) {
			return null;
		}
		return sale.getId();
	}
}
