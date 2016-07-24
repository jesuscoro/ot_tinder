package net.opentrends.model.repository.datasource;


import net.opentrends.domain.model.Gender;
import net.opentrends.domain.model.Person;
import net.opentrends.model.dao.PersonDao;
import net.opentrends.model.dao.PersonDaoImpl;

import java.util.List;

/**
 * Implementacion del almacen de datos de base de datos
 */
public class DataBasePersonDataStore implements PersonDataStore {

    /**
     * Objeto dao para acceder a los datos persistidos
     */
    PersonDao personDao;

    /**
     * Constructor unico sin parametrizar que instancia un objeto de tipo {@link PersonDaoImpl}
     */
    public DataBasePersonDataStore() {
        personDao = new PersonDaoImpl();
    }

    @Override
    public void getPersons(Gender gender, final PersonListCallback callback) {
        personDao.getPersons(gender, new PersonDao.GetPersistedPersonsCallback() {
            @Override
            public void onGetPersistedPersons(List<Person> persons) {
                callback.onGetPersons(persons);
            }

            @Override
            public void onGetPersistedPersonsError(String message) {
                callback.onGetPersonsError(message);
            }
        });
    }

    @Override
    public void savePersons(List<Person> persons, final SavePersonsCallback callback) {

        personDao.savePersons(persons, new PersonDao.SavePersonsCallback() {
            @Override
            public void onSavePersons(List<Person> persistedPersons) {
                callback.onSave(persistedPersons);
            }

            @Override
            public void onSavePersonsError(String message) {
                callback.onSaveError(message);
            }
        });
    }
}
