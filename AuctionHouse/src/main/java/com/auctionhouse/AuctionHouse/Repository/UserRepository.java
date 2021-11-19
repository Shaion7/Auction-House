package com.auctionhouse.AuctionHouse.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.auctionhouse.AuctionHouse.Entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "SELECT * FROM User WHERE username = :username", nativeQuery = true)
    User findUserByUsername(@Param("username") String username);
}
