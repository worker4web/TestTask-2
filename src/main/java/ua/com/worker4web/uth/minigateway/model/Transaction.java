package ua.com.worker4web.uth.minigateway.model;


import javax.persistence.*;

/**
 * Created by worker4web on 11/14/2017.
 */

@Entity
public class Transaction {

    @Id
    private Long id;

    @Column(nullable = false, length = 25)
    private String userName;

    @Column(nullable = false, length = 50)
    private String apiKey;

    @Transient
    private String transactionKey;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false, length = 19)
    private String accountNumber;

    @Column(nullable = false, length = 4)
    private String expirationDate;

    @Transient
    private String cscCode;

    @Column(nullable = false, length = 50)
    private String customerAccountCode;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, length = 100)
    private String street;

    @Column(nullable = false, length = 50)
    private String city;

    @Column(nullable = false, length = 2)
    private String state;

    @Column(nullable = false, length = 15)
    private String zipCode;

    @Column(nullable = false, length = 15)
    private String phoneNumber;

    @Column(nullable = false, length = 100)
    private String description;

    @Column(nullable = false, length = 10)
    private String responseCode;

    @Column(nullable = false)
    private String responseMessage;

    @Column(nullable = false, length = 6)
    private String authCode;

    @Column(nullable = false, length = 1)
    private String avsResultCode;

    @Column(nullable = false, length = 1)
    private String cvvResultCode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName.length()>25 ? userName.substring(0, 24): userName;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey.length()>50 ? apiKey.substring(0, 49):apiKey;
    }

    public String getTransactionKey() {
        return transactionKey;
    }

    public void setTransactionKey(String transactionKey) {
        this.transactionKey = transactionKey;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber.length()>19 ? accountNumber.substring(0, 18): accountNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCscCode() {
        return cscCode;
    }

    public void setCscCode(String cscCode) {
        this.cscCode = cscCode;
    }

    public String getCustomerAccountCode() {
        return customerAccountCode;
    }

    public void setCustomerAccountCode(String customerAccountCode) {
        this.customerAccountCode = customerAccountCode.length()>50 ? customerAccountCode.substring(0, 49) : customerAccountCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.length()>50 ? firstName.substring(0, 49) : firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.length()>50 ? lastName.substring(0, 49) : lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street.length()>100 ? street.substring(0,99) : street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city.length()>50 ? city.substring(0, 49) : city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state.length()>2 ? state.substring(0,1) : state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode.length()>15 ? zipCode.substring(0, 14) : zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber.length()>15 ? phoneNumber.substring(0, 14) : phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description.length()>100 ? description.substring(0, 99) : description;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode.length()>10 ? responseCode.substring(0, 9) : responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage.length()>255 ? responseMessage.substring(0,254) : responseMessage;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode.length()>6 ? authCode.substring(0, 5) : authCode;
    }

    public String getAvsResultCode() {
        return avsResultCode;
    }

    public void setAvsResultCode(String avsResultCode) {
        this.avsResultCode = avsResultCode.length()>1 ? avsResultCode.substring(0,0) : avsResultCode;
    }

    public String getCvvResultCode() {
        return cvvResultCode;
    }

    public void setCvvResultCode(String cvvResultCode) {
        this.cvvResultCode = cvvResultCode.length()>1 ? cvvResultCode.substring(0,0) : cvvResultCode;
    }
}
