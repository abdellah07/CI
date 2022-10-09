package fr.unice.bff.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.bff.dto.tables.Table;
import fr.unice.bff.exception.TableNotFoundException;
import fr.unice.bff.util.ExternalCall;
import fr.unice.bff.util.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;

import static fr.unice.bff.util.ExternalCall.call;

@Service
public class TableService {

    private Logger logger = LoggerFactory.getLogger(TableService.class);
    private static String tableBaseUrl = BaseUrl.getDinning();
    private static final String tableInfoURL = tableBaseUrl + "/tables/";

    public boolean tableIsTaken(int tableId) throws TableNotFoundException {
        String json = call(tableInfoURL + tableId);
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
        String json = call(tableInfoURL + tableId);
        Table tableInfoResponse;
        try {
            tableInfoResponse = JsonMapper.objectMapper.readValue(json, Table.class);
            return tableInfoResponse;
        } catch (JsonProcessingException e) {
            logger.error("Problem in getting table info");
            throw new TableNotFoundException(tableId);
        }
    }

    private List<Table> getTables() throws TableNotFoundException {
        String json = call(tableInfoURL);
        try {
            List<Table> tablesList = Arrays.asList(JsonMapper.objectMapper.readValue(json, Table[].class));
            return tablesList;
        } catch (JsonProcessingException e) {
            logger.error("Problem in getting table info");
            int i = 0;
            throw new TableNotFoundException(i);
        }
    }

    public ResponseEntity<String> getAllTable(){
        ResponseEntity<String> response = ExternalCall.send(tableInfoURL, Table[].class);
        return response;
    }
}
