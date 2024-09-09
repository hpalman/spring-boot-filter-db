package com.example.filterdb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    @GetMapping("/log")
    public String logData() {
        return "Data logged successfully!";
    }
}
