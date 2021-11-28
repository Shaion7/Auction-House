package com.auctionhouse.AuctionHouse.Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bids")
public class Bid{
	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "itemId")
    private Long itemId;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "bidAmount")
    private double bidAmount;
    
    public Bid() {
    	
    }

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public double getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(double bidAmount) {
		this.bidAmount = bidAmount;
	}

}
