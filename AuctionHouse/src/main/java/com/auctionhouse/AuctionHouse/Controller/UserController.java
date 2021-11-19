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

import com.auctionhouse.AuctionHouse.Entities.User;
import com.auctionhouse.AuctionHouse.Services.UserService;

@RestController
@RequestMapping("/api/")
public class UserController {
	
	private final UserService userService;
	
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
	
    @GetMapping("/getUser")
    public User getUser(@RequestBody String username) {
        System.out.println("username");
        System.out.println(username);
        return userService.getUser(username);
    }
    
    @PostMapping("/registerUser")
    public User registerUser(@RequestBody User user) {
    	return userService.registerUser(user);
    }
    
}
