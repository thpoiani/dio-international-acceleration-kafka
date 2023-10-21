package me.dio.producertwitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ProducerTwitterApplication {
	Logger logger = LoggerFactory.getLogger(ProducerTwitterApplication.class);

	@Autowired
	private TwitterReader twitterReader;

	public static void main(String[] args) {
		SpringApplication.run(ProducerTwitterApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			List<String> strings = twitterReader.loadTweets(10);
			logger.info("Tweets: {}", strings);
		};
	}

}
