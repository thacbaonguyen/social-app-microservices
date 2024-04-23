package com.thacbao.social.hashtagservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HashTagServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HashTagServiceApplication.class, args);
	}

}
