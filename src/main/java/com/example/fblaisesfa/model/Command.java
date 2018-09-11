package com.example.fblaisesfa.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "command")
public class Command extends SfaDomain implements SfaOperation {

	@ManyToOne()
	@JoinColumn(name = "CLIENT_ID", nullable = true)
	private Client client;
	@ManyToOne()
	@JoinColumn(name = "USER_ID", nullable = false)
	private User user;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	@OneToMany(mappedBy = "command", cascade = CascadeType.REMOVE)
	private List<CommandEntry> entries = new ArrayList<>();
	@Enumerated(EnumType.STRING)
	private CommandState state = CommandState.Opened;
	@Column(name = "commandnumber", unique = true)
	private String commandNumber;

	public Command() {
	}

	public Command(User user, Client client, Date date) {
		super();
		this.user = user;
		this.client = client;
		this.date = date;
	}

	public Command(User user, Date date) {
		super();
		this.user = user;
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<CommandEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<CommandEntry> entries) {
		this.entries = entries;
	}

	public String getCommandNumber() {
		return commandNumber;
	}

	public void setCommandNumber(String commandNumber) {
		this.commandNumber = commandNumber;
	}

	public float getTotalAmount() {
		float amount = 0;
		for (CommandEntry entry : entries) {
			amount = amount + entry.getTotalAmount();
		}
		return amount;

	}

	public float getTotalQuantity() {
		float quantity = 0;
		for (CommandEntry entry : entries) {
			quantity = quantity + entry.getQuantity();
		}
		return quantity;

	}

	public void addEntry(CommandEntry entry) {
		if (entries.contains(entry)) {
			entries.remove(entry);
		}
		entries.add(entry);
	}

	public void copyValuesFrom(Command commandWithNewValues) {
		this.client = commandWithNewValues.client;
		this.date = commandWithNewValues.date;
		this.commandNumber = commandWithNewValues.commandNumber;
	}

	public String getClientCommercialName() {
		if (client == null) {
			return "";
		}
		return client.getCommercialName();
	}

	public void removeEntry(CommandEntry entry) {
		if (entries.contains(entry)) {
			entries.remove(entry);
		}
	}

	public CommandState getState() {
		return state;
	}

	public void setState(CommandState state) {
		this.state = state;
	}

	public void execute() {
		this.state = CommandState.NOT_DELIVERED;
	}

	public boolean isOpened() {
		return state == CommandState.Opened;
	}

	public Long getClientId() {
		if (client == null) {
			return null;
		}
		return client.getId();
	}

	public boolean isDelivered() {
		return this.state == CommandState.NOT_DELIVERED;
	}
}
