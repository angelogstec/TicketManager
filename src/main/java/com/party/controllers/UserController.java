package com.party.controllers;

import com.party.dtos.PartyDto;
import com.party.dtos.UserDto;
import com.party.models.PartyModel;
import com.party.models.UserModel;
import com.party.services.UserService;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDto userDto){
        if (userService.existByLogin(userDto.getLogin())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exist.");
        }
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUser(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "id") UUID id){
        Optional<UserModel> userModelOptional = userService.findOneUser(id);
        return userModelOptional.<ResponseEntity<Object>>map(userModel -> ResponseEntity.status(HttpStatus.OK).body(userModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id")UUID id){
        Optional<UserModel> userModelOptional = userService.findOneUser(id);
        if (userModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("User delete successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id")UUID id, @RequestBody @Valid UserDto userDto){
        Optional<UserModel> userModelOptional = userService.findOneUser(id);
        if (userModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Party not found.");
        }
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setUserId(userModelOptional.get().getUserId());
        return ResponseEntity.status(HttpStatus.OK).body(userModel);
    }
}
