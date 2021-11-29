package com.auctionhouse.AuctionHouse.Services;

import java.util.List;

import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auctionhouse.AuctionHouse.Entities.Bid;
import com.auctionhouse.AuctionHouse.Entities.GetItemOnSale;
import com.auctionhouse.AuctionHouse.Entities.PostItemOnSale;
import com.auctionhouse.AuctionHouse.Entities.User;
import com.auctionhouse.AuctionHouse.Repository.BidRepository;
import com.auctionhouse.AuctionHouse.Repository.GetItemOnSaleRepository;
import com.auctionhouse.AuctionHouse.Repository.PostItemOnSaleRepository;
import com.auctionhouse.AuctionHouse.Repository.UserRepository;

@Service
public class Services {
    private final UserRepository userRepository;
    private final GetItemOnSaleRepository getItemOnSaleRepository;
    private final PostItemOnSaleRepository postItemOnSaleRepository;
    private final BidRepository bidRepository;

    @Autowired
    public Services(UserRepository userRepository, 
    				GetItemOnSaleRepository getItemOnSaleRepository,
                    PostItemOnSaleRepository postItemOnSaleRepository,
                    BidRepository bidRepository) {
        this.userRepository = userRepository;
        this.getItemOnSaleRepository = getItemOnSaleRepository;
        this.postItemOnSaleRepository = postItemOnSaleRepository;
        this.bidRepository = bidRepository;
    }

    public User getUser(String username) {
        System.out.println("username (services): " + username);
        return userRepository.findUserByUsername(username);
    }
    
    public User registerUser(User user) {
    	return userRepository.save(user);
    }
    
    public List<PostItemOnSale> getAllItemOnSale() {
    	return postItemOnSaleRepository.findAll();
    }

    public List<GetItemOnSale> getItemsOnSaleForUser(String username) {
        Long userId = userRepository.findUserByUsername(username).getUserId();
        return getItemOnSaleRepository.findItemOnSaleByUserId(userId);
    }
    
    public PostItemOnSale postItemOnSale(PostItemOnSale item) {
    	Long categoryId = postItemOnSaleRepository.getCategoryId(item.getCategory());
    	Long conditionId = postItemOnSaleRepository.getConditionId(item.getCondition());
    	Long locationId = postItemOnSaleRepository.getLocationId(item.getLocation());
    	item = postItemOnSaleRepository.save(item);
    	
//    	// Category Relation
    	postItemOnSaleRepository.postItemCategory(item.getItemId(), categoryId);
//    	
//    	// Condition Relation
    	postItemOnSaleRepository.postItemCondition(item.getItemId(), conditionId);
//    	
//    	// Location Relation
    	postItemOnSaleRepository.postItemLocation(item.getItemId(), locationId);
    	
    	// Add to bid table
    	bidRepository.save(item.getItemId(), null, item.getInitialPrice());
    	
    	return item;
    }
    
    public List<Bid> getUserBids(String username){
    	Long userId = userRepository.findUserByUsername(username).getUserId();
        return bidRepository.getUserBids(userId);
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
