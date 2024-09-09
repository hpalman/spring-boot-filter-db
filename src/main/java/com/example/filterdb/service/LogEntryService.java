package com.example.filterdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.filterdb.entity.LogEntry;
import com.example.filterdb.repository.LogEntryRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LogEntryService {

    @Autowired
    private LogEntryRepository logEntryRepository;

    public void saveLogEntry(String data) {
    	log.info(data);
        LogEntry logEntry = new LogEntry(data);
        logEntryRepository.save(logEntry);
    }
}
