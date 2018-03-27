package com.dwalczak.newsreader;

import com.dwalczak.newsreader.filter.RestLoggingFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@SpringBootApplication
@EnableSwagger2
public class NewsreaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsreaderApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean restLoggingFilter() {
		FilterRegistrationBean<RestLoggingFilter> registration = new FilterRegistrationBean<>();
		registration.setFilter(new RestLoggingFilter());
		registration.addUrlPatterns("/news/*");
		registration.setOrder(HIGHEST_PRECEDENCE);
		return registration;
	}

}
