package com.myretail;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;




@SpringBootApplication(scanBasePackages = { "com.myretail.*"})
@EnableScheduling
public class MyRetailApplication {

	public final static Logger logger = LoggerFactory.getLogger(MyRetailApplication.class);
    
	public static void main(String[] args) {
		SpringApplication.run(MyRetailApplication.class, args);
	}

	

}
