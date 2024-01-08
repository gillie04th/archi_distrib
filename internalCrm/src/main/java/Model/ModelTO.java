package Model;

import thrift.InternalLeadDto;

public class ModelTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String zipCode;
    private String city;
    private String country;
    private String creationDate;
    private String companyName;
    private String state;
    private double annualRevenue;

    public ModelTO(String firstName, String lastName, String phoneNumber, String address, String zipCode, String city, String country, String creationDate, String companyName, String state) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.creationDate = creationDate;
        this.companyName = companyName;
        this.state = state;
    }

    public ModelTO setAnnualRevenue(double annualRevenue) {
        this.annualRevenue = annualRevenue;
        return this;
    }

    public double getAnnualRevenue() {
        return annualRevenue;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ModelTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ModelTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public ModelTO setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public ModelTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCity() {
        return city;
    }

    public ModelTO setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public ModelTO setCreationDate(String creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public ModelTO setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getState() {
        return state;
    }
    public ModelTO setState(String state) {
        this.state = state;
        return this;
    }

    public InternalLeadDto convertToVirtualLeadDTO() {
        InternalLeadDto internalLeadDto = new InternalLeadDto(firstName + " " + lastName, phoneNumber, address, zipCode, city, country, creationDate, companyName, state);
        return internalLeadDto;
    }
}