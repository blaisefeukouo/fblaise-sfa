package com.example.fblaisesfa.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "counter")
public class Counter extends SfaDomain {
	@Version
	private long version;
	private Long commandCounter;
	private Long saleCounter;

	public Counter() {
	}

	public Counter(Long commandCounter, Long saleCounter) {
		super();
		this.commandCounter = commandCounter;
		this.saleCounter = saleCounter;
	}

	public Long getCommandCounter() {
		return commandCounter;
	}

	public void setCommandCounter(Long commandCounter) {
		this.commandCounter = commandCounter;
	}

	public Long getSaleCounter() {
		return saleCounter;
	}

	public void setSaleCounter(Long saleCounter) {
		this.saleCounter = saleCounter;
	}

	public void incrementCommandCounter() {
		this.commandCounter = commandCounter + 1;
	}

	public void incrementSaleCounter() {
		this.saleCounter = saleCounter + 1;
	}

}