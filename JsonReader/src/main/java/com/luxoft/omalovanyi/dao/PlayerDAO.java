package com.luxoft.omalovanyi.dao;

import com.luxoft.omalovanyi.model.Player;

import java.util.List;

/**
 * Created by maoleks on 6/10/2016.
 */
public interface PlayerDAO {

    void insertListPlayers(List<Player> listPlayers);

    void clearListPlayers();

    List<Player> getListPlayers();

}
