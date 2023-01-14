package com.party.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;


@Entity
@Table(name = "TB_PARTY")
public class PartyModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID partyId;

    @Column(unique = true, nullable = false, length = 200)
    private String name;
    @Column(nullable = false)
    private String date;
    @Column(nullable = false, length = 200)
    private String place;
    @Column(nullable = false, length = 10)
    private Float price;


    public UUID getPartyId() {
        return partyId;
    }

    public void setPartyId(UUID id) {
        this.partyId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
