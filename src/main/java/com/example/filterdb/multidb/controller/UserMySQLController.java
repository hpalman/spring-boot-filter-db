package com.example.filterdb.multidb.controller;

import com.example.filterdb.multidb.model.UserMySQL;
import com.example.filterdb.multidb.service.UserMySQLService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mysql/users")
public class UserMySQLController {

    private final UserMySQLService userService;

    public UserMySQLController(UserMySQLService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserMySQL> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserMySQL> getUserById(@PathVariable Long id) {
        UserMySQL user = userService.findById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // id가 null 이면 insert, null 이 아니면 업데이트
    @PostMapping("/create")
    public ResponseEntity<UserMySQL> createUser(@RequestBody UserMySQL user) {
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    // 무조건 insert
    @PostMapping("/insert")
    public ResponseEntity<UserMySQL> insert(@RequestBody UserMySQL user) {
        userService.insert(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserMySQL> updateUser(@PathVariable Long id, @RequestBody UserMySQL user) {
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
