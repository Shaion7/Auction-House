package com.auctionhouse.AuctionHouse.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item_on_sale")
public class ItemOnSale {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "initialPrice")
	private String initialPrice;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "timeLimit")
	private String timeLimit;
	
	public ItemOnSale() {
		
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(String initialPrice) {
		this.initialPrice = initialPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}
	
	
}