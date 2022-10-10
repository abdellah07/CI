package fr.unice.bff.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.bff.dto.payment.PaymentInfo;
import fr.unice.bff.exception.TableNotFoundException;
import fr.unice.bff.util.ExternalCall;
import fr.unice.bff.util.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private static String dinningURL = BaseUrl.getDinning();
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);
    private static final String tableOrderSubdirectory = "/tableOrders";
    private static final String billSubdirectory = "/prepare";

    @Autowired
    private TableService tableService;

    public void getPaymentInfo() {

    }

    public boolean validatePayment(int tableid) throws TableNotFoundException, JsonProcessingException {
        String tableOrderId = tableService.getTableInfo(tableid).getTableOrderId();
        String url = dinningURL + "/" + tableOrderId + "/" + billSubdirectory;
        String json = ExternalCall.call(url);
        PaymentInfo paymentInfo = JsonMapper.objectMapper.readValue(json, PaymentInfo.class);
        return paymentInfo.getBilled() != null;
    }
}
