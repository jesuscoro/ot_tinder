package net.opentrends.model.restclient.model;


import net.opentrends.domain.model.Person;

import java.util.List;

/**
 * Clase que modela la respuesta del servidor.
 * <p/>
 * Contiene una lista de elementos {@link Result} que a su vez contiene un objeto de tipo
 * {@link Person} que es lo que realmente nos interesa. El resto de informacion
 * es prescindible
 */
public class ServerResponse {

    private List<Result> results;
    private Object info;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }
}
