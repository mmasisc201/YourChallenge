package com.project.appstions.yourchallenge.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Felipe on 29/11/2015.
 */
public class EntityUser implements Serializable {

    private String name;
    private String nickname;
    private String pass;
    private String firstName;
    private String urlImage;
    private String email;
    private String token;
    private EntityCountry countryUser;
    private EntityGameCategory gameCategoryUser;
    private Date birthDate;
    private int playerId;


    public EntityUser(String name, String nickname, String pass, String firstName, String urlImage, String email,
                      String token, EntityCountry countryUser, EntityGameCategory gameCategoryUser, Date birthDate, int playerId) {
        this.name = name;
        this.nickname = nickname;
        this.pass = pass;
        this.firstName = firstName;
        this.urlImage = urlImage;
        this.email = email;
        this.token = token;
        this.countryUser = countryUser;
        this.gameCategoryUser = gameCategoryUser;
        this.birthDate = birthDate;
        this.playerId=playerId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public EntityCountry getCountryUser() {
        return countryUser;
    }

    public void setCountryUser(EntityCountry countryUser) {
        this.countryUser = countryUser;
    }

    public EntityGameCategory getGameCategoryUser() {
        return gameCategoryUser;
    }

    public void setGameCategoryUser(EntityGameCategory gameCategoryUser) {
        this.gameCategoryUser = gameCategoryUser;
    }

    public EntityUser() {
        this.name = "";
        this.nickname = "";
        this.pass = "";
        this.firstName = "";
        this.urlImage = "";

        this.email = "";
        this.token = "";
        this.countryUser = new EntityCountry();
        this.gameCategoryUser = new EntityGameCategory();
        this.birthDate= new Date();
        this.playerId=0;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
