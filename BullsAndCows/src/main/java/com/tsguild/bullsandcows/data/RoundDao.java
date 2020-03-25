/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.bullsandcows.data;

import com.tsguild.bullsandcows.model.Round;
import java.util.List;

/**
 *
 * @author Patricia
 */
public interface RoundDao {
    
    Round createNewRound (Round round);
    
    List<Round> getAllRoundsByGameId (int gameId);
    
}
