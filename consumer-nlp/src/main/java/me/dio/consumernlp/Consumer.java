package me.dio.consumernlp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);
    private static final String TOPIC = "students.twitter.posts";

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @KafkaListener(id = "consumer-nlp", topics = TOPIC)
    public void listen(String value,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        var sentiment = sentimentAnalysisService.analyzeSentiment(value);
        logger.info("Consumed event from topic {}: key = {} value = {} sentiment = {}", topic, key, value, sentiment);
    }
}
