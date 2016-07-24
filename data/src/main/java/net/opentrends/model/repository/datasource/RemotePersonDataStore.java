package net.opentrends.model.repository.datasource;


import android.util.Log;

import net.opentrends.domain.model.Gender;
import net.opentrends.domain.model.Person;
import net.opentrends.model.restclient.PersonRestClient;
import net.opentrends.model.restclient.PersonRestClientImpl;

import java.util.List;

/**
 * Implementacion del almacen de datos remoto
 */
public class RemotePersonDataStore implements PersonDataStore {

    /**
     * Objeto Rest para realizar las llamadas remotas
     */
    PersonRestClient restClient;

    /**
     * Constructor unico por defecto que instancia un objeto de tipo rest client para hacer las
     * llamadas remotas
     */
    public RemotePersonDataStore() {
        this.restClient = new PersonRestClientImpl();
    }

    @Override
    public void getPersons(Gender gender, final PersonListCallback callback) {
        restClient.getRemotePersons(gender, new PersonRestClient.GetRemotePersonsCallback() {
            @Override
            public void onGetRemotePersons(List<Person> persons) {
                callback.onGetPersons(persons);
            }

            @Override
            public void onGetRemotePersonsError(String message) {
                callback.onGetPersonsError(message);
            }
        });
    }

    /**
     * {@inheritDoc}
     * <p/>
     * NO IMPLEMENTADO. No se puede almacenar datos en remoto
     *
     * @param persons  Las personas a actualizar
     * @param callback Callback a ejecutar una vez completada la peticion
     */
    @Override
    public void savePersons(List<Person> persons, SavePersonsCallback callback) {
        Log.w(RemotePersonDataStore.class.getName(), "NO IMPLEMENTADO");
        callback.onSaveError("No puedes utilizar este almacen para modificar o insertar personas");
    }
}
