import com.luxoft.omalovanyi.dao.PlayerDAO;
import com.luxoft.omalovanyi.dao.impl.PlayerDAOImpl;
import com.luxoft.omalovanyi.model.Player;
import com.luxoft.omalovanyi.util.Utility;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maoleks on 6/17/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/testApplicationContext.xml")
public class PlayerDAOTest {

    private List<Player> expectListPlayers = new ArrayList<>();
    private List<Player> resultListPlayers = new ArrayList<>();
    private List<Player> listPlayers = new ArrayList<>();
    private Player player = new Player();

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Autowired
    private PlayerDAO playerDao;

    @Before
    public void initObject() {
        player.setName("Alex");
        player.setBiography("Text");
        player.setPhotoInclude("Yes");
        player.setSpecialPlayer("No");
        player.setPosition("Forward");
        player.setNumber("10");
        player.setCaps("6");
        player.setCountGoals("2");
        player.setClub("Shakhtar Donetsk");
        player.setLeague("Premier League (Ukraine)");
        player.setDateBirthday("10/03/1992");
        expectListPlayers.add(player);
    }

    @Test
    public void testInsertListPlayers() {
        playerDao.insertListPlayers(expectListPlayers);
        resultListPlayers=checkResult();
        Assert.assertEquals(expectListPlayers,resultListPlayers);
        Assert.assertTrue(resultListPlayers.size()==1);
        Assert.assertTrue("Alex".equals(resultListPlayers.get(0).getName()));
    }

    @Test
    public void testClearListPlayers() {
        playerDao.insertListPlayers(expectListPlayers);
        playerDao.clearListPlayers();
        resultListPlayers=checkResult();
        Assert.assertTrue(resultListPlayers.size()==0);
    }

    @Test
    public void testGetListPlayersOrderByDateBirthday() {
        playerDao.insertListPlayers(expectListPlayers);
        resultListPlayers=checkResult();
        listPlayers=playerDao.getListPlayers();
        Assert.assertEquals(resultListPlayers,listPlayers);
    }

    private List<Player> checkResult() {
        String sql = "select * from UEFA2016";
        return jdbcTemplate.query(sql, new RowMapper<Player>() {
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
        });
    }
}
