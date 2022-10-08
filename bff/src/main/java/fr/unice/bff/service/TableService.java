package fr.unice.bff.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.bff.dto.tables.Table;
import fr.unice.bff.exception.TableNotFoundException;
import fr.unice.bff.util.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static fr.unice.bff.util.ExternalCall.call;

@Service
public class TableService {

    private Logger logger = LoggerFactory.getLogger(TableService.class);
    private static final String tableInfoURL =  "http://localhost:3001/tables/";

    public boolean tableIsTaken(int tableId) throws TableNotFoundException {
        String json = call(tableInfoURL+tableId);
        Table tableInfoResponse;
        try {
            tableInfoResponse = JsonMapper.objectMapper.readValue(json, Table.class);
            return tableInfoResponse.isTaken() || tableInfoResponse.getTableOrderId() == null;
        } catch (JsonProcessingException e) {
            logger.error("Problem in getting table info");
            throw new TableNotFoundException(tableId);
        }
    }

    public Table getTableInfo(int tableId) throws TableNotFoundException {
        String json = call(tableInfoURL+tableId);
        Table tableInfoResponse;
        try {
            tableInfoResponse = JsonMapper.objectMapper.readValue(json, Table.class);
            return tableInfoResponse;
        } catch (JsonProcessingException e) {
            logger.error("Problem in getting table info");
            throw new TableNotFoundException(tableId);
        }
    }
}
