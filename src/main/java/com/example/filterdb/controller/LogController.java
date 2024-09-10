package com.example.filterdb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    @GetMapping("/non-filter/api") // /log
    public String logData() {
        return "[non-filter] Data logged successfully!";
    }

    @GetMapping("/filter/api") // /nolog
    public String noLogData() {
        return "[/api/filter] Nolog Data logged successfully!";
    }
}
