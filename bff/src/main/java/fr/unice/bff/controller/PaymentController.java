package fr.unice.bff.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.bff.dto.payment.Payment;
import fr.unice.bff.dto.payment.PaymentInfo;
import fr.unice.bff.exception.TableNotFoundException;
import fr.unice.bff.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping(BASE_URI + "/{tableId}/{price}")
    public void validatePayment(@PathVariable("tableId") int tableId, @PathVariable("tableId") int price) throws TableNotFoundException, JsonProcessingException {
        logger.info("calling Payment service ");
        paymentService.validatePayment(tableId, price);
    }

    @GetMapping(BASE_URI + "/info/{tableId}")
    public PaymentInfo paymentInfo(@PathVariable("tableId") int tableId) throws TableNotFoundException, JsonProcessingException {
        return paymentService.getPaymentInfo(tableId);
    }
}
