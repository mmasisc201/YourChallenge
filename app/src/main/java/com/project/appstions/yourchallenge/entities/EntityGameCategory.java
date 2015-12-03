package com.project.appstions.yourchallenge.entities;

/**
 * Created by Felipe on 29/11/2015.
 */
public class EntityGameCategory {

    private String descriptionCategory;
    private int idGameCategory;

    public EntityGameCategory(String descriptionCategory, int idGameCategory) {
        this.descriptionCategory = descriptionCategory;
        this.idGameCategory = idGameCategory;
    }

    public EntityGameCategory() {
        this.descriptionCategory = "";
        this.idGameCategory = 0;
    }

    public String getDescriptionCategory() {
        return descriptionCategory;
    }

    public void setDescriptionCategory(String descriptionCategory) {
        this.descriptionCategory = descriptionCategory;
    }

    public int getIdGameCategory() {
        return idGameCategory;
    }

    public void setIdGameCategory(int idGameCategory) {
        this.idGameCategory = idGameCategory;
    }

}
