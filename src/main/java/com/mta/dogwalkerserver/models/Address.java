package com.mta.dogwalkerserver.models;

import org.elasticsearch.geometry.utils.Geohash;
import org.apache.lucene.util.GeoHashUtils;

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

    pri




    public Address() {
    }

    public Address(String city, String streetName, int houseNumber, int zipCode, String geoHashLocation) {
        this.city = city;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.geoHashLocation = geoHashLocation;
    }

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
