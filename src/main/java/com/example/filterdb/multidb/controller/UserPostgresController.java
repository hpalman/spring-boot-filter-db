package com.example.filterdb.multidb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.filterdb.multidb.model.UserPostgres;
import com.example.filterdb.multidb.service.UserPostgresService;

@RestController
@RequestMapping("/api/postgres/users")
public class UserPostgresController {

    private final UserPostgresService userService;

    public UserPostgresController(UserPostgresService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserPostgres> getAllUsers() {
    	//List list = new ArrayList<>();
    	//UserPostgres userPostgres = new UserPostgres();
    	//userPostgres.setId(100L);
    	//userPostgres.setName("d");
    	//list.add(userPostgres);
    	//return list;
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserPostgres> getUserById(@PathVariable Long id) {
        UserPostgres user = userService.findById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<UserPostgres> createUser(@RequestBody UserPostgres user) {
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserPostgres> updateUser(@PathVariable Long id, @RequestBody UserPostgres user) {
        user.setId(id);
        userService.save(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
