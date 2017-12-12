package ua.com.worker4web.uth.minigateway.impl;

import net.authorize.api.contract.v1.CreateTransactionResponse;
import net.authorize.api.contract.v1.MessageTypeEnum;
import net.authorize.api.contract.v1.TransactionResponse;
import org.springframework.stereotype.Service;
import ua.com.worker4web.uth.minigateway.intf.Parser;
import ua.com.worker4web.uth.minigateway.model.AuthTransaction;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

/**
 * Created by worker4web on 11/15/2017.
 */
@Service
public class ParserImpl implements Parser {
    public void parse(AuthTransaction authTransaction, String responseContent) {

        CreateTransactionResponse response = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CreateTransactionResponse.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader(responseContent);
            response = (CreateTransactionResponse) unmarshaller.unmarshal(reader);
        } catch (JAXBException ex) {
            authTransaction = null;
        }

        if (response != null) {
            authTransaction.setResultCode(response.getMessages().getResultCode().toString());
            if (response.getMessages().getResultCode() == MessageTypeEnum.OK) {
                TransactionResponse result = response.getTransactionResponse();
                if (result.getMessages() != null) {
                    authTransaction.setTransId(result.getTransId());  //50 //System.out.println("Successfully created transaction with Transaction ID: " + result.getTransId());
                    authTransaction.setAvsResultCode(result.getAvsResultCode()); //1
                    authTransaction.setCvvResultCode(result.getCvvResultCode()); //1
                    authTransaction.setResponseCode(result.getResponseCode()); //1 //System.out.println("Response Code: " + result.getResponseCode());
                    authTransaction.setMessageCode(result.getMessages().getMessage().get(0).getCode());  //10 System.out.println("Message Code: " + result.getMessages().getMessage().get(0).getCode());
                    authTransaction.setMessageText(result.getMessages().getMessage().get(0).getDescription()); //System.out.println("Description: " + result.getMessages().getMessage().get(0).getDescription());
                    authTransaction.setAuthCode(result.getAuthCode()); //6 //System.out.println("Auth Code: " + result.getAuthCode());
                } else {
                    System.out.println("Failed Transaction.");
                    if (response.getTransactionResponse().getErrors() != null) {
                        authTransaction.setMessageCode(response.getTransactionResponse().getErrors().getError().get(0).getErrorCode());//System.out.println("Error Code: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorCode());
                        authTransaction.setMessageText(response.getTransactionResponse().getErrors().getError().get(0).getErrorText());//System.out.println("Error message: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorText());
                    }
                }
            } else {
                System.out.println("Failed Transaction.");
                if (response.getTransactionResponse() != null && response.getTransactionResponse().getErrors() != null) {
                    authTransaction.setMessageCode(response.getTransactionResponse().getErrors().getError().get(0).getErrorCode());//System.out.println("Error Code: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorCode());
                    authTransaction.setMessageText(response.getTransactionResponse().getErrors().getError().get(0).getErrorText());//System.out.println("Error message: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorText());
                } else {
                    authTransaction.setMessageCode(response.getMessages().getMessage().get(0).getCode()); //System.out.println("Error Code: " + response.getMessages().getMessage().get(0).getCode());
                    authTransaction.setMessageText(response.getMessages().getMessage().get(0).getText()); //System.out.println("Error message: " + response.getMessages().getMessage().get(0).getText());
                }
            }
        } else {
            System.out.println("Null Response.");
        }
    }
}
