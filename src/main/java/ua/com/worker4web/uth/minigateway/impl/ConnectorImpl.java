package ua.com.worker4web.uth.minigateway.impl;

import net.authorize.Environment;
import net.authorize.api.contract.v1.*;
import net.authorize.api.controller.CreateTransactionController;
import net.authorize.api.controller.base.ApiOperationBase;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import ua.com.worker4web.uth.minigateway.intf.Connector;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 * Created by worker4web on 11/15/2017.
 */
@Service
public class ConnectorImpl implements Connector {

    public String sendData(String requestContent) {

        JSONParser parser = new JSONParser();
        JSONObject object;
        try {
            object = (JSONObject)parser.parse(requestContent);
        }
        catch(Exception pe) {
            System.out.println(pe.toString());
            object = null;
        }

        if(object!=null) {
            ApiOperationBase.setEnvironment(Environment.SANDBOX);

            MerchantAuthenticationType merchantAuthenticationType = new MerchantAuthenticationType();
            merchantAuthenticationType.setName((String) object.get("ApiKey"));
            merchantAuthenticationType.setTransactionKey((String) object.get("TransactionKey"));
            ApiOperationBase.setMerchantAuthentication(merchantAuthenticationType);

            // Populate the payment data
            PaymentType paymentType = new PaymentType();
            CreditCardType creditCard = new CreditCardType();
            creditCard.setCardNumber((String) object.get("CustomerId"));
            creditCard.setExpirationDate((String) object.get("ExpirationDate"));
            // Set the token specific info
            creditCard.setIsPaymentToken(true);
            creditCard.setCryptogram("EjRWeJASNFZ4kBI0VniQEjRWeJA=");
            paymentType.setCreditCard(creditCard);

            // Create the payment transaction request
            TransactionRequestType txnRequest = new TransactionRequestType();
            txnRequest.setTransactionType(TransactionTypeEnum.AUTH_CAPTURE_TRANSACTION.value());
            txnRequest.setPayment(paymentType);
            txnRequest.setAmount(new BigDecimal((String)object.get("Amount")).setScale(2, RoundingMode.CEILING));
            CustomerAddressType customerAddressType = new CustomerAddressType();
            customerAddressType.setFirstName((String) object.get("BillToFirstName"));
            customerAddressType.setLastName((String) object.get("BillToLastName"));
            customerAddressType.setAddress((String) object.get("BillToAddress"));
            customerAddressType.setCity((String) object.get("BillToCity"));
            customerAddressType.setState((String) object.get("BillToState"));
            customerAddressType.setZip((String) object.get("BillToZip"));
            customerAddressType.setPhoneNumber((String) object.get("BillToPhone_number"));
            txnRequest.setBillTo(customerAddressType);

            // Make the API Request
            CreateTransactionRequest apiRequest = new CreateTransactionRequest();
            apiRequest.setTransactionRequest(txnRequest);
            CreateTransactionController controller = new CreateTransactionController(apiRequest);
            CreateTransactionResponse response = controller.getApiResponse();

            try {
                JAXBContext jc = JAXBContext.newInstance(CreateTransactionResponse.class);
                Marshaller jaxbMarshaller = jc.createMarshaller();
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                StringWriter sw = new StringWriter();
                jaxbMarshaller.marshal(response, sw);
                return sw.toString();
            }
            catch(JAXBException ex) {
                return "";
            }
        }
        else
            return "";
    }
}
