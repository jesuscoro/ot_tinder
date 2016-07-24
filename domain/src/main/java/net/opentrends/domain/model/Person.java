package net.opentrends.domain.model;

/**
 * Clase que modela una persona del dominio. Sera tambien la utilizada por la vista para mostrar los
 * datos.
 */
public class Person {

    private long id;
    private String gender;
    private String number;
    private String thumbnail;
    private String picture;
    private String descritpion;
    private boolean favourite;

    public Person() {
    }

    public Person(long id, String gender, String number, String thumbnail, String picture, String descritpion, boolean favourite) {
        this.id = id;
        this.gender = gender;
        this.number = number;
        this.thumbnail = thumbnail;
        this.picture = picture;
        this.descritpion = descritpion;
        this.favourite = favourite;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }

    public boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}
