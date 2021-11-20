package com.auctionhouse.AuctionHouse.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auctionhouse.AuctionHouse.Entities.ItemOnSale;
import com.auctionhouse.AuctionHouse.Entities.User;
import com.auctionhouse.AuctionHouse.Repository.ItemOnSaleRepository;
import com.auctionhouse.AuctionHouse.Repository.UserRepository;

@Service
public class Services {
    private final UserRepository userRepository;
    private final ItemOnSaleRepository itemOnSaleRepository;

    @Autowired
    public Services(UserRepository userRepository, 
    				ItemOnSaleRepository itemOnSaleRepository) {
        this.userRepository = userRepository;
        this.itemOnSaleRepository = itemOnSaleRepository;
    }

    public User getUser(String username) {
        System.out.println("username (services): " + username);
        return userRepository.findUserByUsername(username);
    }
    
    public User registerUser(User user) {
    	return userRepository.save(user);
    }
    
    public List<ItemOnSale> getAllItemOnSale() {
    	return itemOnSaleRepository.findAll();
    }
}
