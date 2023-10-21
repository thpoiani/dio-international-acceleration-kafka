package me.dio.consumernlp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;

@SpringBootApplication
public class ConsumerNlpApplication {
    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    public static void main(String[] args) {
        SpringApplication.run(ConsumerNlpApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            kafkaListenerEndpointRegistry.getListenerContainer("consumer-nlp")
                                         .start();
        };
    }

}
