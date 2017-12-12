package com.williamjin.celebrityfragment.model;

/**
 * Created by william on 12/4/17.
 */

public class Celebrity {
    private Integer id;
    private String name;
    private Character gender;
    private String type;
    private Boolean favorite;

    public Celebrity(String name, Character gender, String type, Boolean favorite) {
        this.id = -1;
        this.name = name;
        this.gender = gender;
        this.type = type;
        this.favorite = favorite;
    }

    public Celebrity(Integer id, String name, Character gender, String type, Boolean favorite) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.type = type;
        this.favorite = favorite;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public String toString() {

        return "id: " + id + " " + name + " (" + gender + ") " + type;
    }
}
