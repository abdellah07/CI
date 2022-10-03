package org.example;


public class Test {

    public static <T> String send(String url,T object){
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept","application/json");
        HttpEntity<T> request = new HttpEntity<>(object, headers);
        ResponseEntity<String> result = restTemplate.postForEntity(url, request, String.class);
        return restTemplate.getForObject(url, String.class);
    }
}
