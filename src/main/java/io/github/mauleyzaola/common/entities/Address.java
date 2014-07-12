package io.github.mauleyzaola.common.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Esta es una clase que modela una direccion cualquiera. Lo realmente interesante es que tiene una FK
 * con la tabla COUNTRY
 */
@Entity
@Table(name = "address")
public class Address extends MyBaseClass {
    private String streetNumber;

    private String state;

    private String intNumber;

    private String street;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "countryid", foreignKey = @ForeignKey(name = "address_country"))
    public Country getCountry() {
        return country;
    }

    private Country country;

    public void setCountry(Country country) {
        this.country = country;
    }

    @Column(name = "zipcode", nullable = false, length = 20)
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Column(name = "streetNumber")
    @Type(type="org.hibernate.type.StringClobType")
    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    @Type(type="org.hibernate.type.StringClobType")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "intnumber")
    @Type(type="org.hibernate.type.StringClobType")
    public String getIntNumber() {
        return intNumber;
    }

    public void setIntNumber(String intNumber) {
        this.intNumber = intNumber;
    }

    @Type(type="org.hibernate.type.StringClobType")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(name = "addressline1")
    @Type(type="org.hibernate.type.StringClobType")
    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    @Column(name = "addressline2")
    @Type(type="org.hibernate.type.StringClobType")
    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    @Type(type="org.hibernate.type.StringClobType")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
