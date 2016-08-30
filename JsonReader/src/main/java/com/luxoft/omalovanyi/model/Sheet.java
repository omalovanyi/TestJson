package com.luxoft.omalovanyi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by maoleks on 6/14/2016.
 */
public class Sheet {
    @SerializedName("Players")
    private List<Player> listPlayers;

    public List<Player> getListPlayers() {
        return listPlayers;
    }

    public void setListPlayers(List<Player> listPlayers) {
        this.listPlayers = listPlayers;
    }
}
