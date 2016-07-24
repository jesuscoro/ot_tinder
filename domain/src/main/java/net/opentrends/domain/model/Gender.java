package net.opentrends.domain.model;

/**
 * Enumerado con los posibles sexos
 */
public enum Gender {

    MALE("male"), FEMALE("female");

    private String value;

    Gender(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
