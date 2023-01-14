package com.party.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class TicketDto {
    @NotNull
    private UUID partyId;
    @NotNull
    private UUID userId;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getPartyId() {
        return partyId;
    }

    public void setPartyId(UUID partyId) {
        this.partyId = partyId;
    }
}
