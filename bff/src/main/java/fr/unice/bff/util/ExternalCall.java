package fr.unice.bff.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class ExternalCall {
    private static RestTemplate restTemplate = new RestTemplate();

    private static Logger logger = LoggerFactory.getLogger(ExternalCall.class);

    public static String call(String url) {
        logger.info("calling : " + url);
        return restTemplate.getForObject(url, String.class);
    }

    public static <T> ResponseEntity<String> send(String url, T object) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json");
        HttpEntity<T> request = new HttpEntity<>(object, headers);
        ResponseEntity<String> result = restTemplate.postForEntity(url, request, String.class);
        return result;
    }

    public static ResponseEntity<String> send(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json");
        HttpEntity request = new HttpEntity<>(headers);
        ResponseEntity<String> result = restTemplate.postForEntity(url, request, String.class);
        return result;
    }
}
