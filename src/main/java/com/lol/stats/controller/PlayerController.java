package com.lol.stats.controller;

import com.lol.stats.model.PlayerStats;
import com.lol.stats.service.RiotApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/player")
@CrossOrigin(origins = "*")
public class PlayerController {

    private static final Logger log = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private RiotApiService riotApiService;

    @GetMapping("/stats")
    public ResponseEntity<?> getPlayerStats(
            @RequestParam String gameName,
            @RequestParam String tagLine,
            @RequestParam(defaultValue = "20") int matchCount) {
        
        try {
            log.info("Fetching stats for player: {}#{}", gameName, tagLine);
            PlayerStats stats = riotApiService.getPlayerStats(gameName, tagLine, matchCount);
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            log.error("Error fetching player stats: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
