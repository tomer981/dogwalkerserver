package com.mta.dogwalkerserver.models;

//https://stackoverflow.com/questions/15786129/converting-java-objects-to-json-with-jackson
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.xiaoleilu.hutool.geo.GeoHash;

import javax.persistence.*;

//5.2
@Entity
@Table(name = "ADDRESS")
public class Address {
    @Id
    @GeneratedValue
    @Column(name = "address_Id")
    private int address_Id;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STREET_NAME")
    private String streetName;

    @Column(name = "HOUSE_NUMBER")
    private int houseNumber;

    @Column(name = "ZIP_CODE")
    private int zipCode;

    @Column(name = "GEO_HASH_LOCATION")
    private String geoHashLocation;


//    @Transient
//    @Column(name = "GEO_HASH")
//    private GeoHash geoHash;


    //CONSTRUCTOR
    public Address() {
    }
    public Address(String city, String streetName, int houseNumber, int zipCode, String geoHashLocation) {
        this.city = city;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.geoHashLocation = geoHashLocation;
    }

//    public GeoHash getGeoHash() {
//        return GeoHash.fromGeohashString(geoHashLocation);
//    }
//
//    public void setGeoHash(GeoHash geoHash) {
//        this.geoHash = geoHash;
//    }
//
//    public void setGeoHash(String geoHash) {
//        setGeoHash(GeoHash.fromGeohashString(geoHash));
//    }

    //getter and setter
    public String getGeoHashLocation() {
        return geoHashLocation;
    }
    public void setGeoHashLocation(String geoHashLocation) {
        this.geoHashLocation = geoHashLocation;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getStreetName() {
        return streetName;
    }
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    public int getHouseNumber() {
        return houseNumber;
    }
    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }
    public int getZipCode() {
        return zipCode;
    }
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }



    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", streetName='" + streetName + '\'' +
                ", houseNumber=" + houseNumber +
                ", zipCode=" + zipCode +
                ", geoHashLocation='" + geoHashLocation + '\'' +
                '}';
    }
}
