package com.party.services;

import com.party.models.TicketModel;
import com.party.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Transactional
    public TicketModel save(TicketModel ticketModel){
        return ticketRepository.save(ticketModel);
    }


    public List<TicketModel> findAll() {
        return ticketRepository.findAll();
    }

    public Optional<TicketModel> findOneTicket(UUID id) {
        return ticketRepository.findById(id);
    }

    @Transactional
    public void deleteTicket(UUID id) {
        ticketRepository.deleteById(id);
    }
}
