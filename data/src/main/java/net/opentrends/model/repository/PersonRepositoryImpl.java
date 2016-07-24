package net.opentrends.model.repository;

import net.opentrends.domain.model.Gender;
import net.opentrends.domain.model.Person;
import net.opentrends.domain.repository.PersonRepository;
import net.opentrends.model.repository.datasource.DataBasePersonDataStore;
import net.opentrends.model.repository.datasource.PersonDataStore;
import net.opentrends.model.repository.datasource.RemotePersonDataStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementacion de la capa repositorio del dominio.
 */
public class PersonRepositoryImpl implements PersonRepository {

    private PersonDataStore dataStore;

    /**
     * Constructor unico no parametrizado que instancia un almacen de datos persistidos para
     * obtener los datos de base de datos
     */
    public PersonRepositoryImpl() {
        dataStore = new DataBasePersonDataStore();
    }

    @Override
    public void updatePerson(Person person, final UpdatePersonCallback callback) {
        List<Person> persons = new ArrayList<>();
        persons.add(person);
        dataStore.savePersons(persons, new PersonDataStore.SavePersonsCallback() {
            @Override
            public void onSave(List<Person> persons) {
                callback.onUpdatePerson();
            }

            @Override
            public void onSaveError(String message) {
                callback.onUpdatePersonError(message);
            }
        });
    }

    @Override
    public void getPersons(final Gender gender, final PersonRepository.GetPersonsCallback callback) {
        dataStore.getPersons(gender, new PersonDataStore.PersonListCallback() {

            /**
             * {@inheritDoc}
             *
             * Al obtener el listado de personas de base de datos, si no tenemos ninguna se piden al
             * servicio remoto.
             * @param persons El listado de {@link Person} obtenido
             */
            @Override
            public void onGetPersons(List<Person> persons) {
                if (persons.size() == 0) {
                    // Si no tenemos personas almacenadas en base de datos, hay que ir a buscarlas a remoto
                    getRemotePersons(gender, callback);
                } else {
                    callback.onGetPersons(persons);
                }
            }

            @Override
            public void onGetPersonsError(String message) {
                callback.onError(message);
            }
        });
    }

    /**
     * Pide al almacen de datos remotos el listado de {@link Person} de un {@link Gender} determinado
     *
     * @param gender   El sexo de las personas que queremos obtener
     * @param callback Callback a ejecutar al completarse la peticion
     */
    private void getRemotePersons(final Gender gender, final PersonRepository.GetPersonsCallback callback) {
        new RemotePersonDataStore().getPersons(gender, new PersonDataStore.PersonListCallback() {

            /**
             *
             * @param persons
             */
            @Override
            public void onGetPersons(final List<Person> persons) {
                dataStore.savePersons(persons, new PersonDataStore.SavePersonsCallback() {
                    @Override
                    public void onSave(List<Person> persistedPersons) {
                        callback.onGetPersons(persistedPersons);
                    }

                    @Override
                    public void onSaveError(String message) {
                        callback.onError(message);
                    }
                });
            }

            @Override
            public void onGetPersonsError(String message) {
                callback.onError(message);
            }
        });
    }
}
