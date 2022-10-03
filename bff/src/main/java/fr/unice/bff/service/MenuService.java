package fr.unice.bff.service;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.bff.dto.Item;
import fr.unice.bff.util.JsonMapper;
import org.springframework.stereotype.Service;

import static fr.unice.bff.util.ExternalCall.call;

@Service
public class MenuService {

    private static final String menuUrl = "http://localhost:3000/menus";

    public List<Item> retrieveMenu(){
        String json = call(menuUrl);
        Item[] menuInfoList = new Item[0];
        try {
            JsonMapper.objectMapper.readValue(json, Item[].class);
        } catch (JsonProcessingException e) {
            System.out.println("Problem in calling MENU API");
        }
        return Arrays.stream(menuInfoList).toList();
    }
}
