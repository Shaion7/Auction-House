package com.auctionhouse.AuctionHouse.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auctionhouse.AuctionHouse.Entities.User;
import com.auctionhouse.AuctionHouse.Repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String username) {
        System.out.println("username (services): " + username);
        return userRepository.findUserByUsername(username);
    }
    
    public User registerUser(User user) {
    	return userRepository.save(user);
    }
}
