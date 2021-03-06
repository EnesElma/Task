package com.enes.user.controller;

import com.enes.user.entity.User;
import com.enes.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService service;

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody User user){
        return ResponseEntity.ok(service.create(user));
    }

    @GetMapping("find/{username}")
    public ResponseEntity<?> findUser(@PathVariable String username){
        return ResponseEntity.ok(service.findByUsername(username));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id){
        service.delete(id);
        return ResponseEntity.ok("User delete successful");
    }

}
