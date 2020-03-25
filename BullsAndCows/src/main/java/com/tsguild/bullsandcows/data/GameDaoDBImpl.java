/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.bullsandcows.data;

import com.tsguild.bullsandcows.model.Game;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Patricia
 */

@Repository
// @Profile("database")

public class GameDaoDBImpl implements GameDao{

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Game createGame(Game game) {
        final String sql = "INSERT INTO games (answer) VALUES (?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, game.getAnswer());
            return statement;
        }, keyHolder);
        
        game.setGameId(keyHolder.getKey().intValue());
        
        return game;
    }

    // added 
    @Override
    public Game getById(int gameId) {
        final String sql = "SELECT * FROM games WHERE gameId = ?;";
        return jdbc.queryForObject(sql, new GameMapper(), gameId);
    }

    @Override
    public Game updateGame(Game game) {
        final String sql = "UPDATE games SET answer = ?, isFinished = ? WHERE gameId = ?;";

                jdbc.update(sql,
                game.getAnswer(),
                game.isIsFinished(),
                game.getGameId());
        return game;
    }

    @Override
    public List<Game> getAllGames() {
        final String sql = "SELECT * FROM games;";
        return jdbc.query(sql, new GameMapper());
        }

    @Override
    public String getAnswerFromDB(int gameId) {
        final String sql = "SELECT answer FROM games WHERE gameId = ?;";
        return jdbc.queryForObject(sql, String.class, gameId);
    }
 
    
    public static final class GameMapper implements RowMapper<Game> {

           @Override
           public Game mapRow(ResultSet rs, int index) throws SQLException {
               Game newGame = new Game();
               newGame.setGameId(rs.getInt("gameId"));
               newGame.setAnswer(rs.getString("answer"));
               newGame.setIsFinished(rs.getBoolean("isFinished"));

               return newGame;
           }
       }
    
}
