/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.bullsandcows.service;

import com.tsguild.bullsandcows.data.RoundDao;
import com.tsguild.bullsandcows.model.Game;
import com.tsguild.bullsandcows.model.Round;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author Patricia
 */
@Service
public class RoundServiceImpl implements RoundService {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    GameService gameService;
    
    @Autowired
    RoundDao dao;
    
    private Round round;
    private Game game;
    
    @Override
    public Round createNewRound(Round round) {
        int p =0;
        int e = 0;
        
        String[] guessArray = round.getGuess().split(""); //size is 4
        String[] answerArray = gameService.getAnswerFromDB(round.getGameId()).split("");
        
    for (int i = 0; i < guessArray.length; i++) {
                for (int j = 0; j < answerArray.length; j++) {
                    if (guessArray[i].equalsIgnoreCase(answerArray[j])) {
                        if (i == j) {
                            e += 1;
                        } else {
                          p += 1;
                        }
                    }
                }
            }
    if(e == 4 ) {
        game = gameService.getGameById(round.getGameId());
        gameService.updateGame(game);
    }
        String result = "e:" + e + ":p:" + p;
        round.setResult(result);
       // round.setGameId(round.getGameId());
        round.setTimeOfGuess(LocalDateTime.now());
        return dao.createNewRound(round);

    }

    @Override
    public List<Round> getAllRoundsByGameId(int gameId) {
        List<Round> allRounds = dao.getAllRoundsByGameId(gameId);
        int roundCount =1;
        for (Round round : allRounds) {
            round.setRoundId(roundCount);
            roundCount++;
        }   
        return allRounds;
    }
}
