package com.sf.bcsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.sf.bcsp"})
@ImportResource({"classpath:disconf.xml"})//引入disconf
public class BcspApplication {

	public static void main(String[] args) {
		SpringApplication.run(BcspApplication.class, args);
	}
}
