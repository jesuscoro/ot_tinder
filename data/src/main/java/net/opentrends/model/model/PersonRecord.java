package net.opentrends.model.model;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import net.opentrends.domain.model.Person;

/**
 * Clase persona del modelo. Necesitamos una clase del modelo y otra del dominio, por que el dominio
 * debe ser independiente del resto de modulos, por lo que no puede tener una referencia al modelo
 *
 * @see "http://satyan.github.io/sugar/"
 */
public class PersonRecord extends SugarRecord {

    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_FAVOURITE = "favourite";
    public static final String COLUMN_NUMBER = "number";

    private String gender;

    @Unique
    private String number;
    private String thumbnail;
    private String picture;
    private String descritpion;
    private boolean favourite;

    /**
     * Constructor por defecto sin parametrizar
     */
    @SuppressWarnings("unused")
    public PersonRecord() {
    }

    /**
     * Constructor parametrizado con un objeto de tipo {@link Person}. Convierte un objecto persona
     * del dominio en un objeto persona del modelo.
     *
     * @param person La persona a convertir al tipo del modelo
     */
    public PersonRecord(Person person) {
        this.setGender(person.getGender());
        this.setNumber(person.getNumber());
        this.setThumbnail(person.getThumbnail());
        this.setPicture(person.getPicture());
        this.setDescritpion(person.getDescritpion());
        this.setFavourite(person.getFavourite());
        if (person.getId() != 0) {
            this.setId(person.getId());
        }
    }

    /**
     * Convierte un objeto de tipo Persona del modelo en un objeto de tipo persona del dominio
     *
     * @return El objeto de tipo persona del dominio
     */
    public Person toPerson() {
        Person person = new Person();
        person.setGender(this.getGender());
        person.setNumber(this.getNumber());
        person.setDescritpion(this.getDescritpion());
        person.setThumbnail(this.getThumbnail());
        person.setPicture(this.getPicture());
        person.setFavourite(this.getFavourite());
        person.setId(this.getId());

        return person;
    }

    // ==================================
    // Getters y Setters
    // ==================================
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
