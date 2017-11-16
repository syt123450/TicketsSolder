package com.ticketSolder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ticketSolder.model.dao")
public class TicketsolderApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketsolderApplication.class, args);
	}
}
