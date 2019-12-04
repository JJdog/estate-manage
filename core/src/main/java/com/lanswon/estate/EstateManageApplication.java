package com.lanswon.estate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;


/**
 * start
 * @author jaswine
 */
@SpringBootApplication
@EnableAsync
@EnableFeignClients
public class EstateManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstateManageApplication.class, args);
	}

}
