package com.ayushkumar.journalApp;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
@EnableTransactionManagement
public class JournalApplication {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(JournalApplication.class, args);
		Environment environment = context.getEnvironment();
		log.debug(Arrays.toString(environment.getActiveProfiles()));
		log.debug("context-path : {}",environment.getProperty("server.servlet.context-path"));
		log.info("port : {}",environment.getProperty("server.port"));
	}

	//  it didn't work on config package - need to check this
	@Bean
	public PlatformTransactionManager transactionConfig(MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
