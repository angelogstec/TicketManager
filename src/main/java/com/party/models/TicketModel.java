package com.party.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_TICKET")
public class TicketModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "party_id")
    @NotNull
    private PartyModel partyModel;

    @OneToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private UserModel userModel;

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public PartyModel getPartyModel() {
        return partyModel;
    }

    public void setPartyModel(PartyModel partyModel) {
        this.partyModel = partyModel;
    }
}
