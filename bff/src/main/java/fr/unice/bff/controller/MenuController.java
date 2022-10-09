package fr.unice.bff.controller;

import fr.unice.bff.dto.menu.MenuItem;
import fr.unice.bff.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController

@RequestMapping(path = MenuController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class MenuController {
    public static final String BASE_URI = "/menu";

    private Logger logger = LoggerFactory.getLogger(MenuController.class);
    @Autowired
    private MenuService menuService;

    @GetMapping
    public ResponseEntity<List<MenuItem>> getTheFullMenu() {
        logger.info("MENU SERVICE");
        return ResponseEntity.ok(menuService.retrieveMenu());
    }

}
