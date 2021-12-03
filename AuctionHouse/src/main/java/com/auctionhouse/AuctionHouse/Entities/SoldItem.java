package com.auctionhouse.AuctionHouse.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sold_item")
public class SoldItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long soldItemId;
	
	@Column(name = "itemName")
	private String itemName;
	
	@Column(name = "itemDescription")
	private String itemDescription;
	
	@Column(name = "finalPrice")
	private Double finalPrice;
	
	public SoldItem() {

	}

	public Long getSoldItemId() {
		return soldItemId;
	}

	public void setSoldItemId(Long soldItemId) {
		this.soldItemId = soldItemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public Double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}
	
}	