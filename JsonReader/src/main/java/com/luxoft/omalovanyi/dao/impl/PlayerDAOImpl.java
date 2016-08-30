package com.luxoft.omalovanyi.dao.impl;

import com.luxoft.omalovanyi.dao.PlayerDAO;
import com.luxoft.omalovanyi.model.Player;
import com.luxoft.omalovanyi.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maoleks on 6/10/2016.
 */
@Component
public class PlayerDAOImpl implements PlayerDAO {

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void insertListPlayers(List<Player> listPlayers) {

        String sql = "insert into UEFA2016 (name,bio,is_photo,is_spec_player,position,number_player,caps,cnt_goals,club,league,date_birthday,rating_match1,rating_match2,rating_match3) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        List<Object[]> inputList = new ArrayList<Object[]>();

        for (Player player : listPlayers) {
            Object[] tmp = new Object[0];
                tmp = new Object[]{
                        player.getName(),
                        player.getBiography(),
                        player.getPhotoInclude(),
                        player.getSpecialPlayer(),
                        player.getPosition(),
                        Integer.valueOf(player.getNumber()),
                        Integer.valueOf(player.getCaps()),
                        Integer.valueOf(player.getCountGoals()),
                        player.getClub(),
                        player.getLeague(),
                        Utility.stringToDate(player.getDateBirthday()),
                        player.getRating_match1(),
                        player.getRating_match2(),
                        player.getRating_match3()
                };
            inputList.add(tmp);
        }
        int[] updateCounts = jdbcTemplate.batchUpdate(sql, inputList);
    }

    @Override
    public void clearListPlayers() {
        String sql = "delete from UEFA2016";
        jdbcTemplate.update(sql);

    }

    @Override
    public List<Player> getListPlayers() {
        String sql = "select * from UEFA2016";
        return jdbcTemplate.query(sql, new PlayerRowMapper());
    }

    private static final class PlayerRowMapper implements RowMapper<Player> {

        @Override
        public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
            Player player = new Player();
            player.setName(rs.getString("name"));
            player.setBiography(rs.getString("bio"));
            player.setPhotoInclude(rs.getString("is_photo"));
            player.setSpecialPlayer(rs.getString("is_spec_player"));
            player.setPosition(rs.getString("position"));
            player.setNumber(String.valueOf(rs.getInt("number_player")));
            player.setCaps(String.valueOf(rs.getInt("caps")));
            player.setCountGoals(String.valueOf(rs.getInt("cnt_goals")));
            player.setClub(rs.getString("club"));
            player.setLeague(rs.getString("league"));
            player.setDateBirthday(Utility.dateToString(rs.getDate("date_birthday")));
            player.setRating_match1(rs.getString("rating_match1"));
            player.setRating_match2(rs.getString("rating_match2"));
            player.setRating_match3(rs.getString("rating_match3"));
            return player;
        }
    }
}

