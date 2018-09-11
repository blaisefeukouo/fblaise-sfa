package com.example.fblaisesfa.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "saleentry")
public class SaleEntry extends SfaDomain {

	@ManyToOne()
	@JoinColumn(name = "SALE_ID", nullable = false)
	private Sale sale;

	@ManyToOne()
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	private Product product;
	private int quantity;

	public SaleEntry() {
	}

	public SaleEntry(Sale sale, Product product, int quantity) {
		super();
		this.sale = sale;
		this.product = product;
		this.quantity = quantity;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
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
