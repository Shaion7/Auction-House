package com.auctionhouse.AuctionHouse.Services;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auctionhouse.AuctionHouse.Entities.Bid;
import com.auctionhouse.AuctionHouse.Entities.ExpiredItem;
import com.auctionhouse.AuctionHouse.Entities.GetItemOnSale;
import com.auctionhouse.AuctionHouse.Entities.PostItemOnSale;
import com.auctionhouse.AuctionHouse.Entities.SoldItem;
import com.auctionhouse.AuctionHouse.Entities.User;
import com.auctionhouse.AuctionHouse.Repository.BidRepository;
import com.auctionhouse.AuctionHouse.Repository.ExpiredItemRepository;
import com.auctionhouse.AuctionHouse.Repository.GetItemOnSaleRepository;
import com.auctionhouse.AuctionHouse.Repository.PostItemOnSaleRepository;
import com.auctionhouse.AuctionHouse.Repository.SoldItemRepository;
import com.auctionhouse.AuctionHouse.Repository.UserRepository;

@Service
public class Services {
    private final UserRepository userRepository;
    private final GetItemOnSaleRepository getItemOnSaleRepository;
    private final PostItemOnSaleRepository postItemOnSaleRepository;
    private final BidRepository bidRepository;
    private final SoldItemRepository soldItemRepository;
    private final ExpiredItemRepository expiredItemRepository;

    @Autowired
    public Services(UserRepository userRepository, 
    				GetItemOnSaleRepository getItemOnSaleRepository,
                    PostItemOnSaleRepository postItemOnSaleRepository,
                    BidRepository bidRepository,
                    SoldItemRepository soldItemRepository,
                    ExpiredItemRepository expiredItemRepository) {
        this.userRepository = userRepository;
        this.getItemOnSaleRepository = getItemOnSaleRepository;
        this.postItemOnSaleRepository = postItemOnSaleRepository;
        this.bidRepository = bidRepository;
        this.soldItemRepository = soldItemRepository;
        this.expiredItemRepository = expiredItemRepository;
    }

    public User getUser(String username) {
        System.out.println("username (services): " + username);
        return userRepository.findUserByUsername(username);
    }
    
    public User registerUser(User user) {
    	return userRepository.save(user);
    }
    
    public List<GetItemOnSale> getAllItemOnSale(User user) {

        Long userId = userRepository.findUserByUsername(user.getUsername()).getUserId();
    	return getItemOnSaleRepository.findAll(userId);
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

        // Add to sell table
        postItemOnSaleRepository.postSells(item.getUserId(), item.getItemId());
    	
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
    public void updateBid(Bid bid) {
    	bidRepository.updateBid(bid.getItemId(), bid.getUserId(), bid.getBidAmount());
    	return;
    }

    public void removeItem(GetItemOnSale item) {
        getItemOnSaleRepository.removeFromSells(item.getItemId());
        getItemOnSaleRepository.removeFromBids(item.getItemId());
        getItemOnSaleRepository.removeFromItemCategory(item.getItemId());
        getItemOnSaleRepository.removeFromItemCondition(item.getItemId());
        getItemOnSaleRepository.removeFromItemLocation(item.getItemId());
        getItemOnSaleRepository.removeFromItemOnSale(item.getItemId());
        return;
    }
    
    public void insertIntoExpiredItem(GetItemOnSale item) {
    	
    	Long itemId = item.getItemId();
    	
    	/* Get current date and time */
    	Timestamp ts = new Timestamp(System.currentTimeMillis());
    	System.out.println("Timestamp: " + ts);
    	
    	/* Create expired item */
    	ExpiredItem expiredItem = new ExpiredItem();
    	expiredItem.setItemName(item.getName());
    	expiredItem.setExpiredTime(ts);
    	expiredItem.setItemDescription(item.getDescription());
    	expiredItem.setItemPrice(item.getInitialPrice());
    	
    	/* Insert expiredItem and get its id */
    	Long expiredItemId = expiredItemRepository.save(expiredItem).getExpiredItemId();
    	System.out.println("expiredItemId: " + expiredItemId);
    	
    	/* Get userId of user who was selling the item */
    	Long userId = getItemOnSaleRepository.getItemUserId(itemId);
    	System.out.println("userId: " + userId);
    	
    	/* Insert into expired */
    	expiredItemRepository.insertIntoExpired(expiredItemId, userId);
    	
//    	ExpiredItem expiredItem = new ExpiredItem();
//    	expiredItem.setExpiredTime(null);
    	
    	return;
    }
    
    public void insertIntoSoldItem(GetItemOnSale item) {
    	
    	Long itemId = item.getItemId();
    	
    	/* Create sold item */
    	SoldItem soldItem = new SoldItem();
    	soldItem.setItemName(item.getName());
    	soldItem.setItemDescription(item.getDescription());
    	soldItem.setFinalPrice(getItemOnSaleRepository.getFinalPrice(itemId));
    	
    	/* Insert soldItem and get its id */
    	Long soldItemId = soldItemRepository.save(soldItem).getSoldItemId();
    	System.out.println("soldItemId: " + soldItemId);
    	
 		/* Get userId of user who sold the item */
    	Long soldUserId = getItemOnSaleRepository.getItemUserId(itemId);
    	System.out.println("soldUserId: " + soldUserId);

    	/* Get userId of user who bought the item */
    	Long boughtUserId = soldItemRepository.getBoughtUserId(itemId);
    	System.out.println("boughtUserId: " + boughtUserId);
    	
    	/* Insert into sold */
    	soldItemRepository.insertIntoSold(soldItemId, soldUserId);
    	
    	/* Insert into bought */
    	soldItemRepository.insertIntoBought(soldItemId, boughtUserId);
    	
    	return;
    }
    
    public List<ExpiredItem> getExpiredItems(User user) {
    	Long userId = userRepository.findUserByUsername(user.getUsername()).getUserId();
    	System.out.println("userId: " + userId);
    	return expiredItemRepository.getExpiredItems(userId);
    	
    }
    
    public List<SoldItem> getSoldItems(User user) {
    	Long userId = userRepository.findUserByUsername(user.getUsername()).getUserId();
    	return soldItemRepository.getSoldItems(userId);
    }
    
    public Double getBidAmount(GetItemOnSale item) {
    	return bidRepository.getBid(item.getItemId()).getBidAmount();
    }

	public Boolean checkIfItemExpired(GetItemOnSale item) {
		Timestamp currentTS = new Timestamp(System.currentTimeMillis());
		// if item expired
		if (item.getTimeLimit().getTime() - currentTS.getTime() <= 0)
			return true;
		return false;
	}

	public Boolean checkIfUserBidded(GetItemOnSale item) {
    	/* Get userId of user who bought the item */
    	Long boughtUserId = soldItemRepository.getBoughtUserId(item.getItemId());
    	System.out.println("boughtUserId: " + boughtUserId);

		if (boughtUserId != null) {
			return true;
		}
		System.out.println("returning false");
		return false;
	}

	public List<GetItemOnSale> getItemsOnSaleByFilter(User user, String category, String condition, String location) {
		Long userId = userRepository.findUserByUsername(user.getUsername()).getUserId();

		/* Category */
		if (category != "" && condition == "" && location == "")
			return getItemOnSaleRepository.findAllByCategory(userId, category);

		/* Condition */
		else if (category == "" && condition != "" && location == "")
			return getItemOnSaleRepository.findAllByCondition(userId, condition);

		/* Location */
		else if (category == "" && condition == "" && location != "")
			return getItemOnSaleRepository.findAllByLocation(userId, location);

		/* Category and Condition */
		else if (category != "" && condition != "" && location == "")
			return getItemOnSaleRepository.findAllByCategoryAndCondition(userId, category, condition);

		/* Category and Location */
		else if (category != "" && condition == "" && location != "")
			return getItemOnSaleRepository.findAllByCategoryAndLocation(userId, category, location);

		/* Condition and Location */
		else if (category == "" && condition != "" && location != "")
			return getItemOnSaleRepository.findAllByConditionAndLocation(userId, condition, location);

		/* Category, Condition, and Location */
		else if (category != "" && condition != "" && location != "")
			return getItemOnSaleRepository.findAllByCategoryAndConditionAndLocation(userId, category, condition, location);
		
		/* None selected */
    	return getItemOnSaleRepository.findAll(userId);
	}
}
