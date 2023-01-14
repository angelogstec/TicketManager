package com.party.services;

import com.party.models.UserModel;
import com.party.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Transactional
    public UserModel save(UserModel userModel){
        return userRepository.save(userModel);
    }
    public List<UserModel> findAll(){
        return userRepository.findAll();
    }

    public Optional<UserModel> findOneUser(UUID id) {
        return userRepository.findById(id);
    }

    @Transactional
    public void deleteUser(UUID id){
        userRepository.deleteById(id);
    }

    public boolean existByLogin(String login){
        return userRepository.existsByLoginLikeIgnoreCase(login);
    }
}
