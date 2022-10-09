package fr.unice.bff.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.bff.dto.preparation.PreparationInfo;
import fr.unice.bff.models.preparation.Preparation;
import fr.unice.bff.models.preparation.PreparationItem;
import fr.unice.bff.models.preparation.PreparationResponse;
import fr.unice.bff.models.preparation.PreparationState;
import fr.unice.bff.util.ExternalCall;
import fr.unice.bff.util.JsonMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreparationService {
    @Value("${restaurant.kitchen}")
    private static String preparationURL = BaseUrl.getKitchen();
    private static final String preparationsSubdirectory = "/preparations";
    /**
     * 'http://localhost:3002/preparations?state=preparationStarted&tableId=1'
     */
    private static final String readyStatePathVariable = "state=" + PreparationState.readyToBeServed.name();
    private static final String upPreparedStatePathVariable = "state=" + PreparationState.preparationStarted.name();

    public PreparationInfo getPreparationInfo(int tableId) throws JsonProcessingException {
        String tableIdPathVariable = "tableId=" + tableId;
        String readyUrl = preparationURL + preparationsSubdirectory + "?" + readyStatePathVariable + "&" + tableIdPathVariable;
        String unPreparedUrl = preparationURL + preparationsSubdirectory + "?" + upPreparedStatePathVariable + "&" + tableIdPathVariable;

        String readyJson = ExternalCall.call(readyUrl);
        String unPreparedJson = ExternalCall.call(unPreparedUrl);

        PreparationResponse readyItems = JsonMapper.objectMapper.readValue(readyJson, PreparationResponse.class);
        PreparationResponse unPreparedItems = JsonMapper.objectMapper.readValue(readyJson, PreparationResponse.class);

        PreparationInfo preparationInfo = new PreparationInfo();

        extractServedItems(unPreparedItems, preparationInfo);
        extractReadyItems(unPreparedItems, preparationInfo);
        extractUnReadyItems(unPreparedItems, preparationInfo);

        return preparationInfo;
    }

    private void extractReadyItems(PreparationResponse unPreparedItems, PreparationInfo preparationInfo) {
        List<Preparation> ready = unPreparedItems.getPreparationList()
                .stream()
                .filter(preparationResponse -> preparationResponse.getTakenForServiceAt() == null)
                .toList();
        for (Preparation preparation : ready) {
            preparationInfo.addReadyItems(preparation.getPreparedItems());
        }
    }

    private void extractServedItems(PreparationResponse unPreparedItems, PreparationInfo preparationInfo) {
        List<Preparation> served = unPreparedItems.getPreparationList()
                .stream()
                .filter(preparationResponse -> preparationResponse.getTakenForServiceAt() != null)
                .toList();
        for (Preparation preparation : served) {
            List<PreparationItem> servedItems = preparation.getPreparedItems().stream().map(preparationItem -> {
                preparationItem.setTakenForService(true);
                return preparationItem;
            }).toList();
            preparationInfo.addServedItems(servedItems);
        }
    }

    private void extractUnReadyItems(PreparationResponse unPreparedItems, PreparationInfo preparationInfo) {
        List<Preparation> unready = unPreparedItems.getPreparationList();
        for (Preparation preparation : unready) {
            preparationInfo.addUnreadyItems(preparation.getPreparedItems());
        }
    }


}
