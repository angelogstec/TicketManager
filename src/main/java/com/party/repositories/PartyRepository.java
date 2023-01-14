package com.party.repositories;

import com.party.models.PartyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PartyRepository extends JpaRepository<PartyModel, UUID> {
    boolean existsByNameLikeIgnoreCase(@NonNull String name);

}
