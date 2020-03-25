/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.bullsandcows.data;

import com.tsguild.bullsandcows.model.Round;
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
public class RoundDaoDBImpl implements RoundDao {
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Round createNewRound(Round round) {
        final String sql = "INSERT INTO rounds(guess, timeOfGuess, result, gameId) VALUES (?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);  
            statement.setString(1, round.getGuess());
            statement.setTimestamp(2, java.sql.Timestamp.valueOf(round.getTimeOfGuess()));
            statement.setString(3, round.getResult());
            statement.setInt(4, round.getGameId());
            return statement;
        }, keyHolder);
        round.setRoundId((keyHolder.getKey().intValue()));
        return round;
    }

    @Override
    public List<Round> getAllRoundsByGameId(int gameId) {
        final String sql = "SELECT * FROM rounds WHERE gameId =?;";
        return jdbc.query(sql, new RoundMapper(), gameId);
        
    }
    
    public static final class RoundMapper implements RowMapper<Round> {

           @Override
           public Round mapRow(ResultSet rs, int index) throws SQLException {
               Round newRound = new Round();
               newRound.setRoundId(rs.getInt("roundId"));
               newRound.setGuess(rs.getString("guess"));
               newRound.setTimeOfGuess(rs.getTimestamp("timeOfGuess").toLocalDateTime());
               newRound.setResult(rs.getString("result"));

               return newRound;
           }
       }    
}
