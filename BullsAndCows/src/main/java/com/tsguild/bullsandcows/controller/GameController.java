/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.bullsandcows.controller;

import com.tsguild.bullsandcows.data.GameDao;
import com.tsguild.bullsandcows.model.Game;
import com.tsguild.bullsandcows.service.GameService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Patricia
 */
@RestController
@RequestMapping("api/game")
public class GameController {
    
    @Autowired
    GameDao dao;
    
    @Autowired
    GameService service;
    
    private Game game;
    
    @GetMapping
    public List<Game> getAllGames() {
        return service.getAllGames();
    }
    
    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getGameById(@PathVariable int gameId) {
        game = service.getGameById(gameId);
        if(game == null) {
            return new ResponseEntity (null, HttpStatus.NOT_FOUND);
        } 
        return ResponseEntity.ok(game);
    }
    
    @PostMapping ("/begin")
    @ResponseStatus (HttpStatus.CREATED)
    public Game createGame () {
        return service.createGame();
    }
    
}