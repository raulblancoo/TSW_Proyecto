package com.tsw.ComPay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={
		"com.tsw.ComPay.Controllers", "com.tsw.ComPay.Dto", "com.tsw.ComPay.Enums", "com.tsw.ComPay.Mapper",
		"com.tsw.ComPay.Models", "com.tsw.ComPay.Repositories", "com.tsw.ComPay.Services", "com.tsw.ComPay.config"})
public class ComPayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComPayApplication.class, args);
	}

}
