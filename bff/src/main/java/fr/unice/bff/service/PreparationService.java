package fr.unice.bff.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.bff.dto.dining.Order;
import fr.unice.bff.dto.preparation.PreparationInfo;
import fr.unice.bff.dto.tables.Table;
import fr.unice.bff.dto.tables.TableInfo;
import fr.unice.bff.exception.TableNotFoundException;
import fr.unice.bff.exception.TableWithoutOrderId;
import fr.unice.bff.models.preparation.Preparation;
import fr.unice.bff.models.preparation.PreparationItem;
import fr.unice.bff.models.preparation.PreparationResponse;
import fr.unice.bff.models.preparation.PreparationState;
import fr.unice.bff.util.ExternalCall;
import fr.unice.bff.util.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreparationService {

    private Logger logger = LoggerFactory.getLogger(PreparationService.class);
    private static String preparationURL = BaseUrl.getKitchen();
    private static final String preparationsSubdirectory = "/preparations";
    /**
     * 'http://localhost:3002/preparations?state=preparationStarted&tableId=1'
     */
    private static final String readyStatePathVariable = "state=" + PreparationState.readyToBeServed.name();
    private static final String upPreparedStatePathVariable = "state=" + PreparationState.preparationStarted.name();

    @Autowired
    private OrderService orderService;

    public PreparationInfo getPreparationInfo(int tableId) throws JsonProcessingException, TableNotFoundException {
        try {
            String tableIdPathVariable = "tableId=" + tableId;
            String baseUrl = preparationURL + preparationsSubdirectory;
            String readyUrl = baseUrl + "?" + readyStatePathVariable + "&" + tableIdPathVariable;
            String unPreparedUrl = baseUrl + "?" + upPreparedStatePathVariable + "&" + tableIdPathVariable;


            PreparationInfo preparationInfo = new PreparationInfo();

            extractReadyItems(readyUrl, preparationInfo);
            extractUnReadyItems(unPreparedUrl, preparationInfo);
            extractServedItems(tableId, baseUrl, preparationInfo);


            logger.info("PREPARATION INFORMATION'S ARE READY");
            return preparationInfo;
        } catch (TableNotFoundException | TableWithoutOrderId exception) {
            logger.warn(exception.getMessage() + " cant process preparation info");
            return new PreparationInfo();
        }

    }

    private void extractReadyItems(String readyUrl, PreparationInfo preparationInfo) throws JsonProcessingException {
        String readyJson = ExternalCall.call(readyUrl);
        Preparation[] readyItems = JsonMapper.objectMapper.readValue(readyJson, Preparation[].class);
        PreparationResponse preparationResponseReady = new PreparationResponse();
        preparationResponseReady.setPreparations(List.of(readyItems));
        List<Preparation> ready = preparationResponseReady.getPreparations()
                .stream()
                .filter(preparationResponse -> preparationResponse.getTakenForServiceAt() == null)
                .toList();
        for (Preparation preparation : ready) {
            preparationInfo.addReadyItems(preparation.getPreparedItems());
        }
    }

    private void extractServedItems(int tableId, String baseUrl, PreparationInfo preparationInfo) throws TableNotFoundException, JsonProcessingException, TableWithoutOrderId {
        List<Preparation> preparations = null;
        preparations = orderService.getAllPreparations(tableId).getPreparations();
        String url = baseUrl + "/";
        for (Preparation preparation : preparations) {
            String json = ExternalCall.call(url + preparation.getId());
            Preparation prep = JsonMapper.objectMapper.readValue(json, Preparation.class);
            if (prep.getTakenForServiceAt() != null) {
                preparationInfo.addServedItems(prep.getPreparedItems());
            }
        }

    }

    private void extractUnReadyItems(String unPreparedUrl, PreparationInfo preparationInfo) throws JsonProcessingException {
        String unPreparedJson = ExternalCall.call(unPreparedUrl);
        Preparation[] unPreparedItems = JsonMapper.objectMapper.readValue(unPreparedJson, Preparation[].class);
        PreparationResponse preparationResponseUnready = new PreparationResponse();
        preparationResponseUnready.setPreparations(List.of(unPreparedItems));
        List<Preparation> unready = preparationResponseUnready.getPreparations();
        for (Preparation preparation : unready) {
            preparationInfo.addUnreadyItems(preparation.getPreparedItems());
        }
    }

    public void serve(int tableId) {
        String baseUrl = preparationURL + preparationsSubdirectory;
        String tableIdPathVariable = "tableId=" + tableId;
        String readyUrl = preparationURL + preparationsSubdirectory + "?" + readyStatePathVariable + "&" + tableIdPathVariable;

        String readyJson = ExternalCall.call(readyUrl);
        try {
            Preparation[] readyItems = JsonMapper.objectMapper.readValue(readyJson, Preparation[].class);

            for (Preparation preparation : readyItems) {
                String prepareURL = baseUrl + "/" + preparation.getId() + "/takenToTable";
                logger.info("sending serve to " + prepareURL);
                ExternalCall.send(prepareURL);
            }
        } catch (JsonProcessingException e) {
            logger.error("Problem in serving Order");
        }

    }


}
