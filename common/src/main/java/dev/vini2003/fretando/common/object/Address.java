package dev.vini2003.fretando.common.object;

import dev.vini2003.fretando.common.object.base.AbstractObject;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Address extends AbstractObject implements Serializable {
    private String street;
    private String number;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String notes;

    public Address() {
    }

    public Address(String street, String number, String city, String state, String postalCode, String country, String notes) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.notes = notes;
    }

    public Address(UUID uuid, String street, String number, String city, String state, String postalCode, String country, String notes) {
        super(uuid);
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.notes = notes;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (!Objects.equals(street, address.street)) return false;
        if (!Objects.equals(number, address.number)) return false;
        if (!Objects.equals(city, address.city)) return false;
        if (!Objects.equals(state, address.state)) return false;
        if (!Objects.equals(postalCode, address.postalCode)) return false;
        if (!Objects.equals(country, address.country)) return false;
        return Objects.equals(notes, address.notes);
    }

    @Override
    public int hashCode() {
        int result = street != null ? street.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return street + ", " + number + ", " + city + ", " + state + " " + postalCode + ", " + country + " - " + notes;
    }
}
