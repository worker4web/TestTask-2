package ua.com.worker4web.uth.minigateway.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.authorize.Environment;
import net.authorize.api.contract.v1.*;
import net.authorize.api.controller.CreateTransactionController;
import net.authorize.api.controller.base.ApiOperationBase;
import org.springframework.stereotype.Service;
import ua.com.worker4web.uth.minigateway.intf.Connector;
import ua.com.worker4web.uth.minigateway.model.AuthTransaction;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 * Created by worker4web on 11/15/2017.
 */
@Service
public class ConnectorImpl implements Connector {

    public String sendData(String requestContent) {

        ObjectMapper mapper = new ObjectMapper();
        AuthTransaction authTransaction;
        try {
            authTransaction = mapper.readValue(requestContent, AuthTransaction.class);
        } catch (IOException ex) {
            return "";
        }

        ApiOperationBase.setEnvironment(Environment.SANDBOX);

        MerchantAuthenticationType merchantAuthenticationType  = new MerchantAuthenticationType();
        merchantAuthenticationType.setName(authTransaction.getApiKey());
        merchantAuthenticationType.setTransactionKey(authTransaction.getTransactionKey());
        ApiOperationBase.setMerchantAuthentication(merchantAuthenticationType);

        // Populate the payment data
        PaymentType paymentType = new PaymentType();
        CreditCardType creditCard = new CreditCardType();
        creditCard.setCardNumber(authTransaction.getCustomerId());
        creditCard.setExpirationDate(authTransaction.getExpirationDate());
        // Set the token specific info
        creditCard.setIsPaymentToken(true);
        creditCard.setCryptogram("EjRWeJASNFZ4kBI0VniQEjRWeJA=");
        paymentType.setCreditCard(creditCard);

        // Create the payment transaction request
        TransactionRequestType txnRequest = new TransactionRequestType();
        txnRequest.setTransactionType(TransactionTypeEnum.AUTH_CAPTURE_TRANSACTION.value());
        txnRequest.setPayment(paymentType);
        txnRequest.setAmount(new BigDecimal(authTransaction.getAmount()).setScale(2, RoundingMode.CEILING));
        CustomerAddressType customerAddressType = new CustomerAddressType();
        customerAddressType.setFirstName(authTransaction.getBillToFirstName());
        customerAddressType.setLastName(authTransaction.getBillToLastName());
        customerAddressType.setAddress(authTransaction.getBillToAddress());
        customerAddressType.setCity(authTransaction.getBillToCity());
        customerAddressType.setState(authTransaction.getBillToState());
        customerAddressType.setZip(authTransaction.getBillToZip());
        customerAddressType.setPhoneNumber(authTransaction.getBillToPhone_number());
        txnRequest.setBillTo(customerAddressType);

        // Make the API Request
        CreateTransactionRequest apiRequest = new CreateTransactionRequest();
        apiRequest.setTransactionRequest(txnRequest);
        CreateTransactionController controller = new CreateTransactionController(apiRequest);
        CreateTransactionResponse response = controller.getApiResponse();

        try {
            if(response !=null) {
                return mapper.writeValueAsString(response);
            }
            else
               return "";
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
