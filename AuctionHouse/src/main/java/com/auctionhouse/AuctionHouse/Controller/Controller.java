package com.auctionhouse.AuctionHouse.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auctionhouse.AuctionHouse.Entities.*;
import com.auctionhouse.AuctionHouse.Services.Services;

@RestController
@RequestMapping("/api/")
public class Controller {
	
	private final Services services;
//	private
	
    @Autowired
    public Controller(Services services) {
        this.services = services;
    }
	
    @GetMapping("/getUser")
    public User getUser(@RequestBody User user) {
        return services.getUser(user.getUsername());
    }
    
    
    @PostMapping("/postUser")
    public User registerUser(@RequestBody User user) {
    	return services.registerUser(user);
    }
 
    @GetMapping("/getAllItemOnSale")
    public List<ItemOnSale> getAllItemOnSale() {
    	return services.getAllItemOnSale();
    }
    
    @PostMapping("/postItemOnSale")
    public ItemOnSale postItemOnSale(@RequestBody ItemOnSale item) {
//    	System.out.println("item: " + item.getName());
    	return services.postItemOnSale(item);
    }
    
//    @GetMapping("/getHighestBid")
//    public Double getCurrentBid(@RequestBody int itemId) {
//    	return services.getCurrentBid(itemId);
//    	
//    }
    
    @PostMapping("/postBid")
    public void postBid(@RequestBody Bid bid) {
    	System.out.println("itemId: " + bid.getItemId());
    	System.out.println("userId: " + bid.getUserId());
    	System.out.println("bidAmount: " + bid.getBidAmount());
    	services.postBid(bid);
    	return;
    }
    
//    @PostMapping("/postBid")
}
