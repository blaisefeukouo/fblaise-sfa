package com.example.fblaisesfa.model;

public class UiOperationEntry extends SfaDomain {
	private SfaOperation command;
	private Product product;
	private int quantity;
	private Client client;

	public UiOperationEntry() {
	}

	public UiOperationEntry(SfaOperation command, Client client, Product product, int quantity) {
		super();
		this.command = command;
		this.client = client;
		this.product = product;
		this.quantity = quantity;
	}

	public UiOperationEntry(SfaOperation command, Product product, int quantity) {
		super();
		this.command = command;
		this.product = product;
		this.quantity = quantity;
	}

	public UiOperationEntry(SfaOperation command, Client client) {
		super();
		this.command = command;
		this.client = client;
	}

	public UiOperationEntry(SfaOperation emptyCommand) {
		this.command = emptyCommand;
	}

	public SfaOperation getCommand() {
		return command;
	}

	public void setCommand(SfaOperation command) {
		this.command = command;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
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

	public Long getClientId() {
		if (client == null) {
			return null;
		}
		return client.getId();
	}

}
