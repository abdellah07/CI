package fr.unice.bff.controller;

import fr.unice.bff.dto.dining.OrderItem;
import fr.unice.bff.dto.tables.Table;
import fr.unice.bff.exception.TableNotFoundException;
import fr.unice.bff.service.OrderService;
import fr.unice.bff.service.TableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
public class TableController {
    public static final String BASE_URI = "/tables";

    private Logger logger = LoggerFactory.getLogger(TableController.class);
    @Autowired
    private OrderService orderService;

    @Autowired
    private TableService tableService;

    @GetMapping(BASE_URI)
    public ResponseEntity<String> createOrder() {
        logger.info("Get All table ");

        return tableService.getAllTable();

    }
}
