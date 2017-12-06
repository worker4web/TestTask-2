package ua.com.worker4web.uth.minigateway.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ua.com.worker4web.uth.minigateway.intf.Transformer;
import ua.com.worker4web.uth.minigateway.model.AuthTransaction;
import ua.com.worker4web.uth.minigateway.model.Transaction;

/**
 * Created by worker4web on 11/15/2017.
 */
@Component
public class TransformerImpl implements Transformer {

    public AuthTransaction transform(Transaction transformer) {
        AuthTransaction authTransaction = new AuthTransaction();

        authTransaction.setUserName(transformer.getUserName());
        authTransaction.setApiKey(transformer.getApiKey());
        authTransaction.setTransactionKey(transformer.getTransactionKey());
        authTransaction.setAmount(Integer.toString(transformer.getAmount()));
        authTransaction.setCustomerId(transformer.getCustomerAccountCode());
        authTransaction.setExpirationDate(transformer.getExpirationDate());
        authTransaction.setBillToFirstName(transformer.getFirstName());
        authTransaction.setBillToLastName(transformer.getLastName());
        authTransaction.setBillToAddress(transformer.getStreet());
        authTransaction.setBillToCity(transformer.getCity());
        authTransaction.setBillToState(transformer.getState());
        authTransaction.setBillToZip(transformer.getZipCode());
        authTransaction.setBillToPhone_number(transformer.getPhoneNumber());
        authTransaction.setDescription(transformer.getDescription());
        authTransaction.setTransaction_fk(transformer);

        return authTransaction;
    }
}
