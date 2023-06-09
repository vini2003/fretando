package dev.vini2003.fretando.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Address(String street, String number, String city, String state, String postalCode, String country, String notes) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.notes = notes;
    }

    private String street;
    private String number;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String notes;
}
