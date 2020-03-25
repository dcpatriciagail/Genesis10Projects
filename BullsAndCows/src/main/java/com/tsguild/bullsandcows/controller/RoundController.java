/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.bullsandcows.controller;

import com.tsguild.bullsandcows.model.Round;
import com.tsguild.bullsandcows.service.RoundService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Patricia
 */
@RestController
@RequestMapping("/api/rounds")
public class RoundController {
    
    @Autowired
    RoundService service;
    
    private Round round;
    
    @GetMapping("/{gameId}")
    public ResponseEntity <List<Round>> getRoundsById (@PathVariable int gameId) {
        List<Round> rounds = service.getAllRoundsByGameId(gameId);
        
        if(rounds.size() == 0){
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        } 
          return ResponseEntity.ok(rounds);
    }
    
    @PostMapping("/guess")
    @ResponseStatus(HttpStatus.CREATED)
    public Round createNewRound(@RequestBody Round round) {
        return service.createNewRound(round);
    }
}
