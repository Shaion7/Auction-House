package com.auctionhouse.AuctionHouse.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auctionhouse.AuctionHouse.Entities.*;
import com.auctionhouse.AuctionHouse.Services.Services;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/")
public class Controller {
	
	private final Services services;
//	private
	
    @Autowired
    public Controller(Services services) {
        this.services = services;
    }
	
// Change to @GetMapping w/ @RequestHeader
    @PostMapping("/getUser")
    public User getUser(@RequestBody User user) {
        return services.getUser(user.getUsername());
    }
    
    
    @PostMapping("/postUser")
    public User registerUser(@RequestBody User user) {
    	return services.registerUser(user);
    }
 
    @PostMapping("/getAllItemOnSale")
    public List<GetItemOnSale> getAllItemOnSale(@RequestBody User user) {
    	return services.getAllItemOnSale(user.getUsername());
    }
// Change to @GetMapping w/ @RequestHeader
    @PostMapping("/getItemsOnSaleForUser")
    public List<GetItemOnSale> getItemsOnSaleForUser(@RequestBody User user) {
        return services.getItemsOnSaleForUser(user.getUsername());
    }
    
    @PostMapping("/postItemOnSale")
    public PostItemOnSale postItemOnSale(@RequestBody PostItemOnSale item) {
    	return services.postItemOnSale(item);
    }
    
//    @GetMapping("/getHighestBid")
//    public Double getCurrentBid(@RequestBody int itemId) {
//    	return services.getCurrentBid(itemId);
//    	
//    }
    
    @PutMapping("/updateBid")
    public void postBid(@RequestBody Bid bid) {
//    	System.out.println("itemId: " + bid.getItemId());
//    	System.out.println("userId: " + bid.getUserId());
//    	System.out.println("bidAmount: " + bid.getBidAmount());
    	services.updateBid(bid);
    	return;
    }
    
    @PostMapping("/getUserBids")
    public List<Bid> getUserBids(@RequestBody User user) {
        return services.getUserBids(user.getUsername());
    }

    @DeleteMapping("/removeItem")
    public void removeItem(@RequestBody GetItemOnSale item) {
        services.removeItem(item);
        return;
    }
    
//    @PostMapping("/checkIfExpired")
//    public void checkIfExpired(@RequestBody User user) {
//    	List<GetItemOnSale> items = services.getItemsOnSaleForUser(user.getUsername());
//
//    	for (GetItemOnSale item : items) {
//        	Double bidAmount = services.getBidAmount(item);
//        	//if item expired
//    		if (true) {
//    			// if user bidded on item before expiration
//	    		if (true)
//	    			//insert into sold_item table
//	    			services.insertIntoExpiredItem(item);
//	    		else
//	    			services.insertIntoSoldItem(item);
//	    		services.removeItem(item);
//    		}
//    	}
//    	
//    		// insert into sold_item table
//    	//else
//    		// insert into expired_item table
//    }
    
    @PostMapping("/insertIntoExpiredItem")
    public void insertIntoExpiredItem(@RequestBody GetItemOnSale item) {
    	services.insertIntoExpiredItem(item);
    	services.removeItem(item);
    	return;
    }
    
    @PostMapping("/insertIntoSoldItem")
    public void insertIntoSoldItem(@RequestBody GetItemOnSale item) {
    	services.insertIntoSoldItem(item);
    	services.removeItem(item);
    	return;
    }
    
    @PostMapping("/getExpiredItems")
    public List<ExpiredItem> getExpiredItems(@RequestBody User user) {
    	return services.getExpiredItems(user);
    }
    
    
    @PostMapping("/getSoldItems")
    public List<SoldItem> getSoldItems(@RequestBody User user) {
    	return services.getSoldItems(user);
    }
}
