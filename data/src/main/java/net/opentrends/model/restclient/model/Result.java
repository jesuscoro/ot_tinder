package net.opentrends.model.restclient.model;


import net.opentrends.domain.model.Person;

/**
 * Clase que modela los objetos obtenidos en la lista de entidades devuelta por el servidor.
 * <p/>
 * Contiene un objeto {@link Person} y otros datos que no nos interesan
 */
public class Result {

    private Person entity;
    private Object obj;

    public Person getEntity() {
        return entity;
    }

    public void setEntity(Person entity) {
        this.entity = entity;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
