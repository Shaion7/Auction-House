package com.auctionhouse.AuctionHouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableEncryptableProperties
public class AuctionHouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuctionHouseApplication.class, args);
	}

}
