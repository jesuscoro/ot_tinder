package net.opentrends.model.dao;

import net.opentrends.domain.model.Gender;
import net.opentrends.domain.model.Person;

import java.util.List;

/**
 * Capa de acceso a datos persistidos. Es el unico punto desde el que se deberia acceder a los
 * objetos de tipo {@link net.opentrends.model.model.PersonRecord}, es decir, a las entidades
 * persistidas
 *
 * @see net.opentrends.model.repository.datasource.DataBasePersonDataStore
 */
public interface PersonDao {

    /**
     * Obtiene un listado de {@link Person} de un {@link Gender} determinado
     *
     * @param gender   El sexo de las personas a obtener
     * @param callback Callback a ejecutar una vez completada la peticion
     */
    void getPersons(Gender gender, GetPersistedPersonsCallback callback);

    /**
     * Inserta o actualiza un listado de {@link Person} en base de datos
     *
     * @param persons  El listado de personas a persistir
     * @param callback Callback a ejecutar una vez completada la peticion
     */
    void savePersons(List<Person> persons, SavePersonsCallback callback);

    /**
     * Interface con los metodos a ejecutar al completarse la peticion del listado de {@link Person}
     * <p/>
     * Expone metodos a ejecutar si la peticion se completo correctamente o para devolver el mensaje
     * de error en caso contrario
     */
    interface GetPersistedPersonsCallback {

        /**
         * Metodo a ejecutar si la peticion se completo correctamente
         *
         * @param persons El listado de personas persistidas
         */
        void onGetPersistedPersons(List<Person> persons);

        /**
         * Metodo a ejecutar si hubo algun error
         *
         * @param message El mensaje de error
         */
        void onGetPersistedPersonsError(String message);
    }

    /**
     * Interface con los metodos a ejecutar al completarse la peticion de persistencia del listado
     * de {@link Person}
     * <p/>
     * Expone metodos a ejecutar si la peticion se completo correctamente o para devolver el mensaje
     * de error en caso contrario
     */
    interface SavePersonsCallback {
        /**
         * Metodo a ejecutar si la peticion se completo correctamente
         *
         * @param persons El listado de personas ya persistido
         */
        void onSavePersons(List<Person> persons);

        /**
         * Metodo a ejecutar si hubo algun error
         *
         * @param message El mensaje de error
         */
        void onSavePersonsError(String message);
    }
}
