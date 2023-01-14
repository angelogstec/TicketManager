package com.party.controllers;

import com.party.dtos.UserDto;
import com.party.dtos.TicketDto;
import com.party.models.PartyModel;
import com.party.models.UserModel;
import com.party.models.TicketModel;
import com.party.services.PartyService;
import com.party.services.UserService;
import com.party.services.TicketService;
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
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;
    @Autowired
    UserService userService;
    @Autowired
    PartyService partyService;

    @PostMapping
    public ResponseEntity<Object> saveTicket(@RequestBody @Valid TicketDto ticketDto){
        Optional<PartyModel> partyModelOptional = partyService.findOneParty(ticketDto.getPartyId());
        Optional<UserModel> userModelOptional = userService.findOneUser(ticketDto.getUserId());
        if (partyModelOptional.isEmpty() || userModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Party or user not found.");
        }
        var ticketModel = new TicketModel();
        ticketModel.setPartyModel(partyModelOptional.get());
        ticketModel.setUserModel(userModelOptional.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.save(ticketModel));
    }

    @GetMapping
    public ResponseEntity<List<TicketModel>> getAllTicket(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ticketService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneTicket(@PathVariable(value = "id") UUID id){
        Optional<TicketModel> ticketModelOptional = ticketService.findOneTicket(id);
        return ticketModelOptional.<ResponseEntity<Object>>map(ticketModel -> ResponseEntity.status(HttpStatus.OK).body(ticketModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket not found."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTicket(@PathVariable(value = "id")UUID id){
        Optional<TicketModel> ticketModelOptional = ticketService.findOneTicket(id);
        if (ticketModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket not found.");
        }
        ticketService.deleteTicket(id);
        return ResponseEntity.status(HttpStatus.OK).body("Ticket delete successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTicket(@PathVariable(value = "id")UUID id, @RequestBody @Valid TicketDto ticketDto){
        Optional<TicketModel> ticketModelOptional = ticketService.findOneTicket(id);
        if (ticketModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket not found.");
        }
        var ticketModel = new TicketModel();
        BeanUtils.copyProperties(ticketDto, ticketModel);
        ticketModel.setId(ticketModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(ticketModel);
    }
}
