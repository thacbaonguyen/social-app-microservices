package com.thacbao.social.shareservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ShareServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShareServiceApplication.class, args);
	}

}
