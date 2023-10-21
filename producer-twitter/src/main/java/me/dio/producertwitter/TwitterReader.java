package me.dio.producertwitter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TwitterReader {

    // https://huggingface.co/datasets/carblacac/twitter-sentiment-analysis
    @Value("classpath:test_62k.txt")
    private Resource resource;

    public List<String> loadTweets(int amount) throws IOException {
        var content = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()), StandardCharsets.UTF_8);

        return Arrays.stream(content.split("\n"))
                     .map(line -> line.split("\t")[1].trim())
                     .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
                         Collections.shuffle(list);
                         return list.stream().limit(amount).toList();
                     }));
    }
}
