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
}
