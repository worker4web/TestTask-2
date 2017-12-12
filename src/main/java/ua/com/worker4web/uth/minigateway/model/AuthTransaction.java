package ua.com.worker4web.uth.minigateway.model;

import javax.persistence.*;

/**
 * Created by worker4web on 11/14/2017.
 */

@Entity
public class AuthTransaction {

    @Id
    private Long id;

    @Transient
    private String userName;

    @Transient
    private String apiKey;

    @Transient
    private String transactionKey;

    @Column(nullable = false, length = 15)
    private String amount;

    @Column(nullable = false, length = 20)
    private String customerId;

    @Transient
    private String expirationDate;

    @Column(nullable = false, length = 50)
    private String billToFirstName;

    @Column(nullable = false, length = 50)
    private String billToLastName;

    @Column(nullable = false, length = 60)
    private String billToAddress;

    @Column(nullable = false, length = 40)
    private String billToCity;

    @Column(nullable = false, length = 2)
    private String billToState;

    @Column(nullable = false, length = 10)
    private String billToZip;

    @Column(nullable = false, length = 25)
    private String billToPhone_number;

    @Column(nullable = false)
    private String description;

    /**
     * Ok or Error
     */
    @Column(nullable = false, length = 5)
    private String resultCode;

    /**
     * The code number for the result.
     */
    @Column(nullable = false, length = 10)
    private String messageCode;

    /**
     * Text explanation of the code for the result.
     */
    @Column(nullable = false)
    private String messageText;

    /**
     * Overall status of the transaction:
     *  1 = Approved
     *  2 = Declined
     *  3 = Error
     *  4 = Held for Review
     */
    @Column(nullable = false, length = 1)
    private String responseCode;

    /**
     * Authorization or approval code.
     */
    @Column(nullable = false, length = 6)
    private String authCode;

    /**
     * Address Verification Service (AVS) response code.
     * Indicates the result of the AVS filter.
     */
    @Column(nullable = false, length = 1)
    private String avsResultCode;

    /**
     * Card code verification (CCV) response code.
     * Indicates result of the CCV filter.
     */
    @Column(nullable = false, length = 1)
    private String cvvResultCode;

    /**
     * The Authorize.Net assigned identification number for a transaction.
     */
    @Column(nullable = false, length = 50)
    private String transId;

    @Column(nullable = false, length = 15)
    private String approvedAmount;

    @OneToOne
    @JoinColumn(name ="ref")
    private Transaction transaction_fk;


    public String getTransactionKey() {
        return transactionKey;
    }

    public void setTransactionKey(String transactionKey) {
        this.transactionKey = transactionKey;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount.length()>15 ? amount.substring(0,14) : amount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId.length()>20 ? customerId.substring(0,19) : customerId;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getBillToFirstName() {
        return billToFirstName;
    }

    public void setBillToFirstName(String billToFirstName) {
        this.billToFirstName = billToFirstName.length()>50 ? billToFirstName.substring(0,49) : billToFirstName;
    }

    public String getBillToLastName() {
        return billToLastName;
    }

    public void setBillToLastName(String billToLastName) {
        this.billToLastName = billToLastName.length()>50 ? billToLastName.substring(0,49) : billToLastName;
    }

    public String getBillToAddress() {
        return billToAddress;
    }

    public void setBillToAddress(String billToAddress) {
        this.billToAddress = billToAddress.length()>60 ? billToAddress.substring(0,59) : billToAddress;
    }

    public String getBillToCity() {
        return billToCity;
    }

    public void setBillToCity(String billToCity) {
        this.billToCity = billToCity.length()>40 ? billToCity.substring(0,39) : billToCity;
    }

    public String getBillToState() {
        return billToState;
    }

    public void setBillToState(String billToState) {
        this.billToState = billToState.length()>2 ? billToState.substring(0,1) : billToState;
    }

    public String getBillToZip() {
        return billToZip;
    }

    public void setBillToZip(String billToZip) {
        this.billToZip = billToZip.length()>10 ? billToZip.substring(0,9) : billToZip;
    }

    public String getBillToPhone_number() {
        return billToPhone_number;
    }

    public void setBillToPhone_number(String billToPhone_number) {
        this.billToPhone_number = billToPhone_number.length()>25 ? billToPhone_number.substring(0,24) : billToPhone_number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description.length()>255 ? description.substring(0,254) : description;
    }

    public String getResultCode() {
        return resultCode==null ? "" : resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode.length()>5 ? resultCode.substring(0,4) : resultCode;
    }

    public String getMessageCode() {
        return messageCode==null ? "" : messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode.length()>10 ? messageCode.substring(0,9) : messageCode;
    }

    public String getMessageText() {
        return messageText==null ? "":messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText.length()>255 ? messageText.substring(0,254) : messageText;
    }

    public String getResponseCode() {
        return responseCode==null?"":responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode.length()>1 ? responseCode.substring(0,0) : responseCode;
    }

    public String getAuthCode() {
        return authCode==null?"":authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode.length()>6 ? authCode.substring(0,5) : authCode;
    }

    public String getAvsResultCode() {
        return avsResultCode==null?"":avsResultCode;
    }

    public void setAvsResultCode(String avsResultCode) {
        this.avsResultCode = avsResultCode.length()>1 ? avsResultCode.substring(0,0) : avsResultCode;
    }

    public String getCvvResultCode() {
        return cvvResultCode==null?"":cvvResultCode;
    }

    public void setCvvResultCode(String cvvResultCode) {
        this.cvvResultCode = cvvResultCode.length()>1 ? cvvResultCode.substring(0,0) : cvvResultCode;
    }

    public String getTransId() {
        return transId==null?"":transId;}

    public void setTransId(String transId) {
        this.transId = transId.length()>50 ? transId.substring(0,49) : transId;
    }

    public String getApprovedAmount() {
        return approvedAmount==null?"":approvedAmount;
    }

    public void setApprovedAmount(String approvedAmount) {
        this.approvedAmount = approvedAmount.length()>15 ? approvedAmount.substring(0,14) : approvedAmount;
    }

    public Transaction getTransaction_fk() {
        return transaction_fk;
    }

    public void setTransaction_fk(Transaction transaction_fk) {
        this.transaction_fk = transaction_fk;
    }
}
