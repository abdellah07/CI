package fr.unice.bff.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.bff.dto.preparation.PreparationInfo;
import fr.unice.bff.service.PreparationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController

@RequestMapping(produces = APPLICATION_JSON_VALUE)
public class PreparationController {
    private Logger logger = LoggerFactory.getLogger(PreparationController.class);
    public static final String BASE_URI = "/preparations";

    @Autowired
    private PreparationService preparationService;

    @GetMapping(BASE_URI + "/{tableId}")
    public ResponseEntity<PreparationInfo> getPreparationInfo(@PathVariable("tableId") int tableId) {
        try {
            return ResponseEntity.ok(preparationService.getPreparationInfo(tableId));
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            return (ResponseEntity<PreparationInfo>) ResponseEntity.badRequest();
        }
    }

    @GetMapping(BASE_URI + "/serve")
    public ResponseEntity<PreparationInfo> serveItems(@PathVariable("tableId") int tableId) {
        preparationService.serve(tableId);
        return null;
    }
}
