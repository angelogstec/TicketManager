package com.party.services;

import com.party.models.PartyModel;
import com.party.repositories.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PartyService {
    @Autowired
    PartyRepository partyRepository;

    @Transactional
    public PartyModel save(PartyModel partyModel){
        return partyRepository.save(partyModel);
    }
    public List<PartyModel> findAll(){
        return partyRepository.findAll();
    }

    public Optional<PartyModel> findOneParty(UUID id) {
        return partyRepository.findById(id);
    }

    @Transactional
    public void deleteParty(UUID id){
        partyRepository.deleteById(id);
    }

    public boolean existByName(String name){
        return partyRepository.existsByNameLikeIgnoreCase(name);
    }
}
