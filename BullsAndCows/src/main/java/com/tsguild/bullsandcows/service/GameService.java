/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.bullsandcows.service;

import com.tsguild.bullsandcows.model.Game;
import java.util.List;

/**
 *
 * @author Patricia
 */
public interface GameService {
    
    Game createGame();
    
    Game getGameById(int gameId);
    
    Game updateGame(Game game);
    
    List<Game> getAllGames();
    
    String getAnswerFromDB(int gameId);
    
}
