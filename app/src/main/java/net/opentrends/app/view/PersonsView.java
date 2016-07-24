package net.opentrends.app.view;


import net.opentrends.app.presenter.PersonPresenterImpl;
import net.opentrends.domain.model.Person;

import java.util.List;

/**
 * Interface a implementar por la vista de personas
 *
 * @see net.opentrends.app.view.fragment.PersonsFragment
 * @see PersonPresenterImpl
 */
public interface PersonsView {

    /**
     * Muestra el progress
     */
    void showProgress();

    /**
     * Oculta el progress
     */
    void hideProgress();

    /**
     * Muestra un mensaje al usuario
     *
     * @param message El mensaje a mostrar
     */
    void showMessage(String message);

    /**
     * Agregar una lista de personas a la vista
     *
     * @param persons Las personas a agregar
     */
    void addPersons(List<Person> persons);

    /**
     * Oculta la capa de seleccion de sexo
     */
    void hideSelectGender();

    /**
     * Notifica cambios en el listado de personas
     */
    void notifyPersonChanged();
}
