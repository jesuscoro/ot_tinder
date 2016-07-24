package net.opentrends.model.restclient;


import net.opentrends.domain.model.Gender;
import net.opentrends.domain.model.Person;

import java.util.List;

/**
 * Interface del cliente para obtener personas
 *
 * @see net.opentrends.model.repository.datasource.RemotePersonDataStore
 */
public interface PersonRestClient {

    /**
     * Realiza la peticion al metodo remoto para obtener el listado de {@link Person}
     *
     * @param gender   El sexo de las personas que se quieren obtener. La peticion remota devolvera
     *                 personas de ambos sexos, pero unicamente se devolveran en el callback las
     *                 del sexo pasado
     * @param callback Callback a ejecutar una vez procesada la respuesta.
     */
    void getRemotePersons(Gender gender, GetRemotePersonsCallback callback);

    /**
     * Callback con los metodos a ejecutar una vez completada la peticion.
     */
    interface GetRemotePersonsCallback {
        void onGetRemotePersons(List<Person> persons);

        void onGetRemotePersonsError(String message);
    }
}
