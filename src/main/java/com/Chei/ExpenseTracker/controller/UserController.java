package com.Chei.ExpenseTracker.controller;

import com.Chei.ExpenseTracker.Entity.Users;

import com.Chei.ExpenseTracker.Entity.UserModel;
import com.Chei.ExpenseTracker.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

@GetMapping("/profile")
    public ResponseEntity<Users> getUser(){
        return new ResponseEntity<Users>(userService.readUser(),HttpStatus.OK);
    }
    @PutMapping("/profile")
    public ResponseEntity<Users> updateUser(@Valid @RequestBody UserModel user){
    return new ResponseEntity<>(userService.updateUser(user),HttpStatus.OK);
    }
    @DeleteMapping("/deactivate")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable Long id){
        userService.deleteUser();
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }
}
