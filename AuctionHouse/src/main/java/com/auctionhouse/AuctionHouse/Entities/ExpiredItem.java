package com.auctionhouse.AuctionHouse.Entities;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "expired_item")
public class ExpiredItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expiredItemId;
	
	@Column(name = "itemName")
	private String itemName;
	
	@Column(name = "expiredTime")
	private Timestamp expiredTime;
	
	@Column(name = "itemPrice")
	private Double itemPrice;
	
	@Column(name = "itemDescription")
	private String itemDescription;

	public ExpiredItem() {}

	public Long getExpiredItemId() {
		return expiredItemId;
	}

	public void setExpiredItemId(Long expiredItemId) {
		this.expiredItemId = expiredItemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Timestamp getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Timestamp expiredTime) {
		this.expiredTime = expiredTime;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
}
