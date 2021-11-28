package com.auctionhouse.AuctionHouse.Services;

import java.util.List;

import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auctionhouse.AuctionHouse.Entities.Bid;
import com.auctionhouse.AuctionHouse.Entities.ItemOnSale;
import com.auctionhouse.AuctionHouse.Entities.User;
import com.auctionhouse.AuctionHouse.Repository.BidRepository;
import com.auctionhouse.AuctionHouse.Repository.ItemOnSaleRepository;
import com.auctionhouse.AuctionHouse.Repository.UserRepository;

@Service
public class Services {
    private final UserRepository userRepository;
    private final ItemOnSaleRepository itemOnSaleRepository;
    private final BidRepository bidRepository;

    @Autowired
    public Services(UserRepository userRepository, 
    				ItemOnSaleRepository itemOnSaleRepository,
                    BidRepository bidRepository) {
        this.userRepository = userRepository;
        this.itemOnSaleRepository = itemOnSaleRepository;
        this.bidRepository = bidRepository;
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
    
    public ItemOnSale postItemOnSale(ItemOnSale item) {
    	Long categoryId = itemOnSaleRepository.getCategoryId(item.getCategory());
    	Long conditionId = itemOnSaleRepository.getConditionId(item.getCondition());
    	Long locationId = itemOnSaleRepository.getLocationId(item.getLocation());
    	item = itemOnSaleRepository.save(item);
    	
//    	// Category Relation
    	itemOnSaleRepository.postItemCategory(item.getItemId(), categoryId);
//    	
//    	// Condition Relation
    	itemOnSaleRepository.postItemCondition(item.getItemId(), conditionId);
//    	
//    	// Location Relation
    	itemOnSaleRepository.postItemLocation(item.getItemId(), locationId);
    	
    	// Add to bid table
    	bidRepository.save(item.getItemId(), null, item.getInitialPrice());
    	
    	return item;
    }
    
// BIDS
//    public Double getCurrentBid(int itemId) {
//    	return bidRepository.findBidAmountByItemId(itemId);
//    }
//    
    public void postBid(Bid bid) {
    	bidRepository.save(bid.getItemId(), bid.getUserId(), bid.getBidAmount());
    	return;
    }
}
