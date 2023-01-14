package com.party.controllers;

import com.party.dtos.PartyDto;
import com.party.models.PartyModel;
import com.party.services.PartyService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/party")
public class PartyController{

    @Autowired
    PartyService partyService;


    @PostMapping
    public ResponseEntity<Object> saveParty(@RequestBody @Valid PartyDto partyDto){
        if (partyService.existByName(partyDto.getName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Party already exist.");
        }
        var partyModel = new PartyModel();
        BeanUtils.copyProperties(partyDto, partyModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(partyService.save(partyModel));
    }

    @GetMapping
    public ResponseEntity<List<PartyModel>> getAllParty(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(partyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneParty(@PathVariable(value = "id")UUID id){
        Optional<PartyModel> partyModelOptional = partyService.findOneParty(id);
        return partyModelOptional.<ResponseEntity<Object>>map(partyModel -> ResponseEntity.status(HttpStatus.OK).body(partyModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Party not found."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParty(@PathVariable(value = "id")UUID id){
        Optional<PartyModel> partyModelOptional = partyService.findOneParty(id);
        if (partyModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Party not found.");
        }
        partyService.deleteParty(id);
        return ResponseEntity.status(HttpStatus.OK).body("Party delete successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParty(@PathVariable(value = "id")UUID id, @RequestBody @Valid PartyDto partyDto){
        Optional<PartyModel> partyModelOptional = partyService.findOneParty(id);
        if (partyModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Party not found.");
        }
        var partyModel = new PartyModel();
        BeanUtils.copyProperties(partyDto, partyModel);
        partyModel.setPartyId(partyModelOptional.get().getPartyId());
        return ResponseEntity.status(HttpStatus.OK).body(partyModel);
    }
}
