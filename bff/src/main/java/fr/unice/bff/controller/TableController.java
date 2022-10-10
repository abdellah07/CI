package fr.unice.bff.controller;

import fr.unice.bff.dto.dining.OrderItem;
import fr.unice.bff.dto.menu.MenuItem;
import fr.unice.bff.dto.preparation.PreparationInfo;
import fr.unice.bff.dto.tables.Table;
import fr.unice.bff.dto.tables.TableInfo;
import fr.unice.bff.exception.TableNotFoundException;
import fr.unice.bff.service.OrderService;
import fr.unice.bff.service.PreparationService;
import fr.unice.bff.service.TableService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
public class TableController {
    public static final String BASE_URI = "/tables";

    private Logger logger = LoggerFactory.getLogger(TableController.class);
    @Autowired
    private PreparationService preparationService;

    @Autowired
    private TableService tableService;

    @GetMapping(BASE_URI)
    public ResponseEntity<TableInfo> getTable() {
        try {
            logger.info("Get All table ");
            List<Table> tables = tableService.getTables();
            TableInfo tableInfos = new TableInfo();
            for (int i = 0; i < tables.size(); i++) {
                PreparationInfo preparation = preparationService.getPreparationInfo(tables.get(i).getNumber());
                if (tables.get(i).getTableOrderId() == null){
                    tableInfos.addAvailableTable(tables.get(i));
                }
                else if (preparation.getReady().size() == 0 && preparation.getUnready().size() == 0 && preparation.getServed().size() == 0) {
                    tableInfos.addAvailableTable(tables.get(i));
                } else if (preparation.getReady().size() > 0) {
                    tableInfos.addOrderReadyTable(tables.get(i));
                } else if (preparation.getUnready().size() > 0) {
                    tableInfos.addInPreparationTable(tables.get(i));
                } else if (preparation.getServed().size() > 0) {
                    tableInfos.addWaitingForPaymentTable(tables.get(i));
                }
            }
            return ResponseEntity.ok(tableInfos);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            return (ResponseEntity<TableInfo>) ResponseEntity.badRequest();
        } catch (TableNotFoundException e) {
            logger.error(e.getMessage());
            return (ResponseEntity<TableInfo>) ResponseEntity.badRequest();
        }


    }
}
