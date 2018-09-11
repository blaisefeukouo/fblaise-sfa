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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sale")
public class Sale extends SfaDomain implements SfaOperation {

	@ManyToOne()
	@JoinColumn(name = "USER_ID", nullable = false)
	private User user;
	@OneToOne
	@JoinColumn(name = "COMMAND_ID", nullable = false)
	private Command command;
	@OneToMany(mappedBy = "sale", cascade = CascadeType.REMOVE)
	private List<SaleEntry> entries = new ArrayList<>();
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	@Enumerated(EnumType.STRING)
	private CommandState state = CommandState.Opened;
	@Column(name = "salenumber", unique = true)
	private String saleNumber;

	public Sale() {
	}

	public Sale(User user, Command command, Date date) {
		super();
		this.user = user;
		this.command = command;
		this.date = date;
	}

	public Sale(User user, Command command) {
		super();
		this.user = user;
		this.command = command;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Command getCommand() {
		return command;
	}

	public Long getCommandId() {
		if (command == null) {
			return null;
		}
		return command.getId();
	}

	public Long getClientId() {
		if (command == null) {
			return null;
		}
		return command.getClientId();
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public List<SaleEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<SaleEntry> entries) {
		this.entries = entries;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSaleNumber() {
		return saleNumber;
	}

	public void setSaleNumber(String saleNumber) {
		this.saleNumber = saleNumber;
	}

	public float getTotalAmount() {
		float amount = 0;
		for (SaleEntry entry : entries) {
			amount = amount + entry.getTotalAmount();
		}
		return amount;

	}

	public CommandState getState() {
		return state;
	}

	public void setState(CommandState state) {
		this.state = state;
	}

	public float getTotalQuantity() {
		float quantity = 0;
		for (SaleEntry entry : entries) {
			quantity = quantity + entry.getQuantity();
		}
		return quantity;

	}

	public void addEntry(SaleEntry entry) {
		if (entries.contains(entry)) {
			entries.remove(entry);
		}
		entries.add(entry);
	}

	public void copyValuesFrom(Sale saleWithNewValues) {
		this.date = saleWithNewValues.date;
		this.saleNumber = saleWithNewValues.saleNumber;
	}

	public void removeEntry(SaleEntry entry) {
		if (entries.contains(entry)) {
			entries.remove(entry);
		}
	}

	public boolean isOpened() {
		return state == CommandState.Opened;
	}

	public Client getClient() {
		if (command == null) {
			return null;
		}
		return command.getClient();
	}

	public String getCommandNumber() {
		if (command == null) {
			return "";
		}
		return command.getCommandNumber();
	}
}
