package com.luxoft.omalovanyi.model;

import com.google.gson.annotations.SerializedName;
import com.luxoft.omalovanyi.util.Utility;

import java.text.ParseException;


/**
 * Created by maoleks on 6/10/2016.
 */

public class Player implements Comparable<Player> {

    private String name;
    @SerializedName("bio")
    private String biography;
    @SerializedName("photo done?")
    private String photoInclude;
    @SerializedName("special player? (eg. key player, promising talent, etc)")
    private String specialPlayer;
    private String position;
    private String number;
    private String caps;
    @SerializedName("goals for country")
    private String countGoals;
    private String club;
    private String league;
    @SerializedName("date of birth")
    private String dateBirthday;
    @SerializedName("")
    private String empty;
    private String rating_match1;
    private String rating_match2;
    private String rating_match3;

    public String getRating_match3() {
        return rating_match3;
    }

    public void setRating_match3(String rating_match3) {
        this.rating_match3 = rating_match3;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPhotoInclude() {
        return photoInclude;
    }

    public void setPhotoInclude(String photoInclude) {
        this.photoInclude = photoInclude;
    }

    public String getSpecialPlayer() {
        return specialPlayer;
    }

    public void setSpecialPlayer(String specialPlayer) {
        this.specialPlayer = specialPlayer;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNumber() {

        if ("".equals(number)) {
            number = "0";
        }

        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCaps() {
        if ("".equals(caps)) {
            caps = "0";
        }
        return caps;
    }

    public void setCaps(String caps) {
        this.caps = caps;
    }

    public String getCountGoals() {

        if ("".equals(countGoals)) {
            caps = "0";
        }
        return countGoals;
    }

    public void setCountGoals(String countGoals) {
        this.countGoals = countGoals;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getDateBirthday() {
        return dateBirthday;
    }

    public void setDateBirthday(String dateBirthday) {
        this.dateBirthday = dateBirthday;
    }

    public String getEmpty() {
        return empty;
    }

    public void setEmpty(String empty) {
        this.empty = empty;
    }

    public String getRating_match1() {
        return rating_match1;
    }

    public void setRating_match1(String rating_match1) {
        this.rating_match1 = rating_match1;
    }

    public String getRating_match2() {
        return rating_match2;
    }

    public void setRating_match2(String rating_match2) {
        this.rating_match2 = rating_match2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (getName() != null ? !getName().equals(player.getName()) : player.getName() != null) return false;
        if (getBiography() != null ? !getBiography().equals(player.getBiography()) : player.getBiography() != null)
            return false;
        if (getPhotoInclude() != null ? !getPhotoInclude().equals(player.getPhotoInclude()) : player.getPhotoInclude() != null)
            return false;
        if (getSpecialPlayer() != null ? !getSpecialPlayer().equals(player.getSpecialPlayer()) : player.getSpecialPlayer() != null)
            return false;
        if (getPosition() != null ? !getPosition().equals(player.getPosition()) : player.getPosition() != null)
            return false;
        if (getNumber() != null ? !getNumber().equals(player.getNumber()) : player.getNumber() != null) return false;
        if (getCaps() != null ? !getCaps().equals(player.getCaps()) : player.getCaps() != null) return false;
        if (getCountGoals() != null ? !getCountGoals().equals(player.getCountGoals()) : player.getCountGoals() != null)
            return false;
        if (getClub() != null ? !getClub().equals(player.getClub()) : player.getClub() != null) return false;
        if (getLeague() != null ? !getLeague().equals(player.getLeague()) : player.getLeague() != null) return false;
        if (getDateBirthday() != null ? !getDateBirthday().equals(player.getDateBirthday()) : player.getDateBirthday() != null)
            return false;
        if (getEmpty() != null ? !getEmpty().equals(player.getEmpty()) : player.getEmpty() != null) return false;
        if (getRating_match1() != null ? !getRating_match1().equals(player.getRating_match1()) : player.getRating_match1() != null)
            return false;
        if (getRating_match2() != null ? !getRating_match2().equals(player.getRating_match2()) : player.getRating_match2() != null)
            return false;
        return !(getRating_match3() != null ? !getRating_match3().equals(player.getRating_match3()) : player.getRating_match3() != null);

    }


    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", club='" + club + '\'' +
                ", league='" + league + '\'' +
                ", dateBirthday='" + dateBirthday + '\'' +
                '}';
    }

    @Override
    public int compareTo(Player player){
           return -1*Utility.stringToDate(this.getDateBirthday()).compareTo(Utility.stringToDate(player.getDateBirthday()));
    }

}
