package net.opentrends.app.presenter;

import net.opentrends.domain.model.Gender;
import net.opentrends.domain.model.Person;

/**
 * Intermediario entre la vista (Activities y Fragments) y la capa de dominio.
 */
public interface PersonPresenter {

    void resume();

    void pause();

    /**
     * Modifica el estado de favorito de un {@link Person}
     *
     * @param person La persona a modificar
     */
    void changeFavourite(Person person);

    /**
     * Obtiene un listado de {@link Person} de un {@link Gender} determinado
     *
     * @param gender El sexo de las personas a obtener
     */
    void getPersons(Gender gender);
}
