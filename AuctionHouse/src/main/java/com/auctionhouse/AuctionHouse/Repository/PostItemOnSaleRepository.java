package com.auctionhouse.AuctionHouse.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.auctionhouse.AuctionHouse.Entities.GetItemOnSale;
import com.auctionhouse.AuctionHouse.Entities.PostItemOnSale;

import java.util.List;

import javax.persistence.Transient;

@Repository
public interface PostItemOnSaleRepository extends JpaRepository<PostItemOnSale, Long> {

//	Category
	@Query(value = "SELECT categoryId FROM category WHERE categoryName = :category", nativeQuery = true)
	Long getCategoryId(@Param("category") String category);
	
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO item_category (itemId, categoryId) VALUES (:itemId, :categoryId)", nativeQuery = true)
	void postItemCategory(@Param("itemId") Long itemId, @Param("categoryId") Long categoryId);
	
	
//	Condition
	@Query(value = "SELECT conditionId FROM cs157a_project.condition WHERE conditionName = :condition", nativeQuery = true)
	Long getConditionId(@Param("condition") String condition);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO item_condition (itemId, conditionId) VALUES (:itemId, :conditionId)", nativeQuery = true)
	void postItemCondition(@Param("itemId") Long itemId, @Param("conditionId") Long categoryId);
	
	
//	Location
	@Query(value = "SELECT locationId FROM location WHERE locationName = :location", nativeQuery = true)
	Long getLocationId(@Param("location") String location);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO item_location (itemId, locationId) VALUES(:itemId, :locationId)", nativeQuery = true)
	void postItemLocation(@Param("itemId") Long itemId, @Param("locationId") Long locationId);
	
	
}