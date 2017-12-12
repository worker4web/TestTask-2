package ua.com.worker4web.uth.minigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.worker4web.uth.minigateway.impl.*;
import ua.com.worker4web.uth.minigateway.model.AuthTransaction;
import ua.com.worker4web.uth.minigateway.model.Transaction;
import ua.com.worker4web.uth.minigateway.repo.AuthTransactionRepo;
import ua.com.worker4web.uth.minigateway.repo.TransactionRepo;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by worker4web on 11/15/2017.
 */

@RestController
public class BaseController {

    @Autowired
    private TransformerImpl transformer;
    @Autowired
    private BackTransformerImpl backTransformer;
    @Autowired
    private GeneratorImpl generator;
    @Autowired
    private ConnectorImpl connector;
    @Autowired
    private ParserImpl parser;

    @Autowired
    TransactionRepo transactionRepo;
    @Autowired
    AuthTransactionRepo authTransactionRepo;


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Transaction handleRequest(HttpServletRequest request,
                                     @RequestParam(value="userName") String userName,
                                     @RequestParam(value="apiKey") String apiKey,
                                     @RequestParam(value="transactionKey") String transactionKey,
                                     @RequestParam(value="amount") int amount,
                                     @RequestParam(value="accountNumber") String accountNumber,
                                     @RequestParam(value="expirationDate") String expirationDate,
                                     @RequestParam(value="cscCode") String cscCode,
                                     @RequestParam(value="customerAccountCode") String customerAccountCode,
                                     @RequestParam(value="firstName") String firstName,
                                     @RequestParam(value="lastName") String lastName,
                                     @RequestParam(value="street") String street,
                                     @RequestParam(value="city") String city,
                                     @RequestParam(value="state") String state,
                                     @RequestParam(value="zipCode") String zipCode,
                                     @RequestParam(value="phoneNumber") String phoneNumber,
                                     @RequestParam(value="description") String description)
    {
        Transaction transaction = new Transaction();
        transaction.setUserName(userName);
        transaction.setApiKey(apiKey);
        transaction.setTransactionKey(transactionKey);
        transaction.setAmount(amount);
        transaction.setAccountNumber(accountNumber);
        transaction.setExpirationDate(expirationDate);
        transaction.setCscCode(cscCode);
        transaction.setCustomerAccountCode(customerAccountCode);
        transaction.setFirstName(firstName);
        transaction.setLastName(lastName);
        transaction.setStreet(street);
        transaction.setCity(city);
        transaction.setState(state);
        transaction.setZipCode(zipCode);
        transaction.setPhoneNumber(phoneNumber);
        transaction.setDescription(description);

        try {
            AuthTransaction authTransaction = transformer.transform(transaction);
            String content = generator.generate(authTransaction);
            String response = connector.sendData(content);
            parser.parse(authTransaction, response);
            backTransformer.transform(transaction, authTransaction);
            transactionRepo.save(transaction);
            authTransactionRepo.save(authTransaction);
        }
        catch (Exception ex) {
            return null;
        }

        return transaction;
    }
}
