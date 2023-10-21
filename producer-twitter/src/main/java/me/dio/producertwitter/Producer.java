package me.dio.producertwitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "students.twitter.posts";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String key, String value) {
        kafkaTemplate.send(TOPIC, key, value)
                     .thenAccept(result -> logger.info(String.format("Produced event to topic %s: key = %-10s value = %s", TOPIC, key, value)))
                     .exceptionally(throwable -> {
                         throwable.printStackTrace();
                         return null;
                     })
                     .join();
    }
}
