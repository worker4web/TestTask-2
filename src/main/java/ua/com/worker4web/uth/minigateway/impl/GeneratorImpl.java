package ua.com.worker4web.uth.minigateway.impl;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import ua.com.worker4web.uth.minigateway.intf.Generator;
import ua.com.worker4web.uth.minigateway.model.AuthTransaction;

/**
 * Created by worker4web on 11/15/2017.
 */
@Component
public class GeneratorImpl implements Generator {

    public String generate(AuthTransaction authTransaction) {

        JSONObject obj = new JSONObject();
        obj.put("ApiKey", authTransaction.getApiKey());
        obj.put("TransactionKey", authTransaction.getTransactionKey());
        obj.put("CustomerId", authTransaction.getCustomerId());
        obj.put("ExpirationDate", authTransaction.getExpirationDate());
        obj.put("Amount", authTransaction.getAmount());
        obj.put("BillToFirstName", authTransaction.getBillToFirstName());
        obj.put("BillToLastName", authTransaction.getBillToLastName());
        obj.put("BillToAddress", authTransaction.getBillToAddress());
        obj.put("BillToCity", authTransaction.getBillToCity());
        obj.put("BillToState", authTransaction.getBillToState());
        obj.put("BillToZip", authTransaction.getBillToZip());
        obj.put("BillToPhone_number", authTransaction.getBillToPhone_number());

        return obj.toJSONString();
    }
}
