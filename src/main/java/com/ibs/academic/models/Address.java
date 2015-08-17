package com.ibs.academic.models;

import org.mongodb.morphia.annotations.Embedded;

/**
 * Created by nathaniel on 3/19/14.
 */

@Embedded
public class Address {
    private String address;
    private String sector;
    private String city;
    private String province;
    private String country;

    public Address() {

    }

    public Address(String address, String sector, String city, String province, String country) {
        this.address = address;
        this.sector = sector;
        this.city = city;
        this.province = province;
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
