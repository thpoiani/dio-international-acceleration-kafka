package me.dio.consumernlp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class ConsumerNlpApplication {
    Logger logger = LoggerFactory.getLogger(ConsumerNlpApplication.class);

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    public static void main(String[] args) {
        SpringApplication.run(ConsumerNlpApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            Stream.of("I just lifted weights and now my arms feel floppy",
                      "good morning!!! its 12:25AM at California.",
                      "hates how just being friends isnt good enough for some",
                      "Great game, West Brom pushed us all the way. Al least we wont go down now with 41 points")
                  .map(sentimentAnalysisService::analyzeSentiment)
                  .forEach(s -> logger.info("Sentiment: {}", s));
        };
    }

}
