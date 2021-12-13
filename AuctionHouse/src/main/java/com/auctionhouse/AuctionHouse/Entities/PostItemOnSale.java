package com.auctionhouse.AuctionHouse.Entities;

import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "item_on_sale")
public class PostItemOnSale {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "initialPrice")
	private Double initialPrice;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "timeLimit")
	private Timestamp timeLimit;

	@Transient
	private String category;
	
	@Transient
	private String condition;

	@Transient
	private String location;

	@Transient
	private Long userId;
	
	
	public PostItemOnSale() {
		
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


	public Double getInitialPrice() {
		return initialPrice;
	}


	public void setInitialPrice(Double initialPrice) {
		this.initialPrice = initialPrice;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Timestamp getTimeLimit() {
		return timeLimit;
	}


	public void setTimeLimit(String timeLimit) {
			Timestamp ts = Timestamp.valueOf(timeLimit);
			this.timeLimit = ts;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getCondition() {
		return condition;
	}


	public void setCondition(String condition) {
		this.condition = condition;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}