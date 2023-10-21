package me.dio.consumernlp;

import edu.stanford.nlp.simple.Sentence;
import edu.stanford.nlp.simple.SentimentClass;
import org.springframework.stereotype.Service;

@Service
public class SentimentAnalysisService {
    // https://corenlp.run/
    public SentimentClass analyzeSentiment(String message) {
        return new Sentence(message).sentiment();
    }
}
