/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.bullsandcows.service;

import com.tsguild.bullsandcows.data.GameDao;
import com.tsguild.bullsandcows.model.Game;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Patricia
 */
@Service
public class GameServiceImpl implements GameService{

    @Autowired
    GameDao dao;

    private Game game;
    private Random rGen = new Random();
    
    
    @Override
    public Game createGame() {
        game = new Game();
        String answer = generateNumberNoDup();
        game.setAnswer(answer);
        game = dao.createGame(game);
        
        if (!game.isIsFinished()) {
            game.setAnswer("****");
        }
        return game;
    }

    private String generateNumberNoDup() {
        List<Integer> numbers  = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
   
        String result = "";
        for (int i = 0; i < 4; i++) {
            result +=  numbers.get(i);
        }
        return result;
    }
    
    
    @Override
    public Game getGameById(int gameId) {
        game = dao.getById(gameId);
        if(!game.isIsFinished()){
            game.setAnswer("****");
        }
        return game;
    }

    @Override
    public Game updateGame(Game game) {
        game.setAnswer(getAnswerFromDB(game.getGameId()));
        game.setIsFinished(true);
        return dao.updateGame(game);
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> allGames = dao.getAllGames();
        for (Game game : allGames) {
            if(!game.isIsFinished()){
                game.setAnswer("");
            }
        }
        return allGames;
        }

    @Override
    public String getAnswerFromDB(int gameId) {
        return dao.getAnswerFromDB(gameId);
    }
    
}
