package ua.com.worker4web.uth.minigateway.impl;

import org.springframework.stereotype.Component;
import ua.com.worker4web.uth.minigateway.intf.BackTransformer;
import ua.com.worker4web.uth.minigateway.model.AuthTransaction;
import ua.com.worker4web.uth.minigateway.model.Transaction;

/**
 * Created by worker4web on 11/15/2017.
 */
@Component
public class BackTransformerImpl implements BackTransformer {

    public void transform(Transaction transaction, AuthTransaction authTransaction) {
        transaction.setResponseCode(authTransaction.getResponseCode());
        transaction.setResponseMessage(authTransaction.getMessageText());
        transaction.setAuthCode(authTransaction.getAuthCode());
        transaction.setAvsResultCode(authTransaction.getAvsResultCode());
        transaction.setCvvResultCode(authTransaction.getCvvResultCode());
    }
}
