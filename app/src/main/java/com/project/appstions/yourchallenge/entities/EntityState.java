package com.project.appstions.yourchallenge.entities;

/**
 * Created by Felipe on 29/11/2015.
 */
public class EntityState {

    private String descriptionState;
    private int idState;
    private String latitudeState;
    private String longitudeState;

    public EntityState(String descriptionState, int idState, String latitudeState, String longitudeState) {
        this.descriptionState = descriptionState;
        this.idState = idState;
        this.latitudeState = latitudeState;
        this.longitudeState = longitudeState;
    }

    public EntityState() {
        this.descriptionState = "";
        this.idState = 0;
        this.latitudeState = "";
        this.longitudeState = "";
    }

    public String getDescriptionState() {
        return descriptionState;
    }

    public void setDescriptionState(String descriptionState) {
        this.descriptionState = descriptionState;
    }

    public int getIdState() {
        return idState;
    }

    public void setIdState(int idState) {
        this.idState = idState;
    }

    public String getLatitudeState() {
        return latitudeState;
    }

    public void setLatitudeState(String latitudeState) {
        this.latitudeState = latitudeState;
    }

    public String getLongitudeState() {
        return longitudeState;
    }

    public void setLongitudeState(String longitudeState) {
        this.longitudeState = longitudeState;
    }

}
