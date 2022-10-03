package fr.unice.bff.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class ExternalCall {
    private static RestTemplate restTemplate = new RestTemplate();

    public static String call(String url){
        return restTemplate.getForObject(url, String.class);
    }

    public static <T> ResponseEntity<String> send(String url,T object){
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept","application/json");
        HttpEntity<T> request = new HttpEntity<>(object, headers);
        ResponseEntity<String> result = restTemplate.postForEntity(url, request, String.class);
        return result;
    }
}
