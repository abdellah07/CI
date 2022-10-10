package fr.unice.bff.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.bff.dto.dining.OrderItem;
import fr.unice.bff.dto.tables.Table;
import fr.unice.bff.exception.TableNotFoundException;
import fr.unice.bff.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
public class PaymentController {
    private Logger logger = LoggerFactory.getLogger(PaymentController.class);
    public static final String BASE_URI = "/payment";

    @Autowired
    private PaymentService paymentService;

    @PostMapping(BASE_URI + "/{tableId}")
    public boolean validatePayment(@PathVariable("tableId") int tableId) throws TableNotFoundException, JsonProcessingException {
        logger.info("calling Payment service ");
        return paymentService.validatePayment(tableId);
    }
}
