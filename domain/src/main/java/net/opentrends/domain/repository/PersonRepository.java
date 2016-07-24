package net.opentrends.domain.repository;

import net.opentrends.domain.model.Gender;
import net.opentrends.domain.model.Person;

import java.util.List;

/**
 * Capa intermedia entre los casos de uso y los datos.
 * <p/>
 * Expone metodos para obtener el listado de {@link Person} y para actualizar un {@link Person}.
 */
public interface PersonRepository {

    /**
     * Obtiene un listado de {@link Person} de un {@link Gender} determinado
     *
     * @param gender   El sexo de las personas a obtener
     * @param callback Callback a ejecutar una vez completada la peticion
     */
    void getPersons(final Gender gender, GetPersonsCallback callback);

    /**
     * Actualiza un {@link Person}
     *
     * @param person   La persona a actualizar
     * @param callback Callback a ejecutar una vez completada la peticion
     */
    void updatePerson(Person person, UpdatePersonCallback callback);

    /**
     * Interface con los metodos a ejecutar al completarse la peticion del listado de {@link Person}.
     * <p/>
     * Expone metodos para devolver el listado si la peticion se completo correctamente o para
     * devolver el mensaje con el error en caso contrario
     */
    interface GetPersonsCallback {
        /**
         * Metodo a ejecutar si la peticion se completo correctamente
         *
         * @param persons El listado de personas
         */
        void onGetPersons(List<Person> persons);

        /**
         * Metodo a ejecutar si hubo algun error
         *
         * @param message El mensaje de error
         */
        void onError(String message);
    }

    /**
     * Interface con los metodos a ejecutar al completarse la peticion de actualizacion de un
     * {@link Person}
     * <p/>
     * Expone metodos a ejecutar si la peticion se completo correctamente o para devolver el mensaje
     * de error en caso contrario
     */
    interface UpdatePersonCallback {

        /**
         * Metodo a ejecutar si se completo la peticion correctamente
         */
        void onUpdatePerson();

        /**
         * Metodo a ejecutar si hubo algun error
         *
         * @param message El mensaje de error
         */
        void onUpdatePersonError(String message);
    }
}
