package com.example.fblaisesfa.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "commandentry")
public class CommandEntry extends SfaDomain {

	@ManyToOne()
	@JoinColumn(name = "COMMAND_ID", nullable = false)
	private Command command;

	@ManyToOne()
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	private Product product;
	private int quantity;

	public CommandEntry() {
	}

	public CommandEntry(Command command, Product product, int quantity) {
		super();
		this.command = command;
		this.product = product;
		this.quantity = quantity;
	}

	public Command getCommand() {
		return command;

	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getTotalAmount() {
		return product.getPrice() * quantity;
	}

}
