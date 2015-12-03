package com.project.appstions.yourchallenge.entities;

import java.util.ArrayList;

/**
 * Created by Felipe on 29/11/2015.
 */
public class EntityCountry {

    private String descriptionCountry;
    private ArrayList<EntityState> states;
    private String idCountry;

    public EntityCountry(String descriptionCountry, ArrayList<EntityState> states, String idCountry) {
        this.descriptionCountry = descriptionCountry;
        this.states = states;
        this.idCountry = idCountry;
    }

    public EntityCountry() {
        this.descriptionCountry = "";
        this.states = new ArrayList<EntityState>();
        this.idCountry = "";
    }

    public String getDescriptionCountry() {
        return descriptionCountry;
    }

    public void setDescriptionCountry(String descriptionCountry) {
        this.descriptionCountry = descriptionCountry;
    }

    public ArrayList<EntityState> getStates() {
        return states;
    }

    public void setStates(ArrayList<EntityState> states) {
        this.states = states;
    }

    public String getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(String idCountry) {
        this.idCountry = idCountry;
    }

}
