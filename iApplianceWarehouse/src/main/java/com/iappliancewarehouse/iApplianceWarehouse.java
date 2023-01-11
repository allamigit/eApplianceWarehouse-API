package com.iappliancewarehouse;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class iApplianceWarehouse implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(iApplianceWarehouse.class, args);
		
		System.out.println("\n ... iApplianceWarehouse started ...\n");
	}

	@Override
	public void run(String... args) {
		
	}

}
