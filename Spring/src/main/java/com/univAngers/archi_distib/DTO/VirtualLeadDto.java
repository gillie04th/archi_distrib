package com.univAngers.archi_distib.DTO;

import thrift.InternalLeadDto;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class VirtualLeadDto {

    private String firstName;
    private String lastName;
    private double annualRevenue;
    private String phone;
    private String street;
    private String postalCode;
    private String city;
    private String country;
    private Calendar creationDate;
    private GeographicPointDto geographicPointTO;
    private String company;
    private String state;


    public VirtualLeadDto() {}

    public VirtualLeadDto(String firstName, String lastName, double annualRevenue, String phone, String street, String postalCode, String city, String country, Calendar creationDate, String company, String state) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.annualRevenue = annualRevenue;
        this.phone = phone;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.creationDate = creationDate;
        this.company = company;
        this.state = state;
    }

    public VirtualLeadDto(InternalLeadDto lead) {
        Calendar calendar = Calendar.getInstance();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(lead.getCreationDate(), formatter);
        calendar.set(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());


        this.firstName = lead.getName().split(" ")[0];
        this.lastName = lead.getName().split(" ")[0];
        this.annualRevenue = lead.getAnnualRevenue();
        this.phone = lead.getPhone();
        this.street = lead.getStreet();
        this.postalCode = lead.getPostalCode();
        this.city = lead.getCity();
        this.country = lead.getCountry();
        this.creationDate = calendar;
        this.company = lead.getCompany();
        this.state = lead.getState();
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getAnnualRevenue() {
        return annualRevenue;
    }

    public void setAnnualRevenue(double annualRevenue) {
        this.annualRevenue = annualRevenue;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public GeographicPointDto getGeographicPointTO() {
        return geographicPointTO;
    }

    public void setGeographicPointTO(GeographicPointDto geographicPointTO) {
        this.geographicPointTO = geographicPointTO;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
