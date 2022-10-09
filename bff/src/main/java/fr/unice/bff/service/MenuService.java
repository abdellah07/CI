package fr.unice.bff.service;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.bff.dto.menu.MenuItem;
import fr.unice.bff.util.JsonMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static fr.unice.bff.util.ExternalCall.call;

@Service
public class MenuService {

    private static String menuBaseUrt = BaseUrl.getMenu();
    private static final String menuUrl = menuBaseUrt + "/menus";

    public List<MenuItem> retrieveMenu() {
        String json = call(menuUrl);
        MenuItem[] menuInfoList = new MenuItem[0];
        try {
            menuInfoList = JsonMapper.objectMapper.readValue(json, MenuItem[].class);
        } catch (JsonProcessingException e) {
            System.out.println("Problem in calling MENU API");
        }
        return Arrays.stream(menuInfoList).toList();
    }
}
