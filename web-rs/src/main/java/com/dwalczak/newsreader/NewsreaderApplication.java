package com.dwalczak.newsreader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "com.dwalczak.newsreader", "com.dwalczak.newsreader.rs.api" })
public class NewsreaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsreaderApplication.class, args);
	}
}
