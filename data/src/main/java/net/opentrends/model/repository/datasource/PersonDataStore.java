package net.opentrends.model.repository.datasource;


import net.opentrends.domain.model.Gender;
import net.opentrends.domain.model.Person;

import java.util.List;

/**
 * Capa intermedia entre el almacen de datos y el repositorio. Puede acceder a distintas fuentes
 * de datos haciendo que el dominio no tenga que preocuparse de donde vienen.
 * <p/>
 * Expone metodos para obtener un listado de {@link Person} o para persistirlo, ademas de interfaces
 * a implementar para procesar el resultado
 *
 * @see net.opentrends.model.repository.PersonRepositoryImpl
 */
public interface PersonDataStore {

    /**
     * Interface con los metodos a ejecutar al completarse la peticion del listado de {@link Person}
     */
    interface PersonListCallback {
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
        void onGetPersonsError(String message);
    }

    /**
     * Interface con los metodos a ejecutar al completarse la peticion del persistencia del listado
     * de {@link Person}
     */
    interface SavePersonsCallback {

        /**
         * Metodo a ejecutar si la peticion se completo correctamente
         *
         * @param persons El listado de personas ya persistido
         */
        void onSave(List<Person> persons);

        /**
         * Metodo a ejecutar si hubo algun error
         *
         * @param message El mensaje de error
         */
        void onSaveError(String message);
    }

    /**
     * Obtiene un listado de {@link Person} de un {@link Gender} determinado
     *
     * @param gender   El sexo de las personas a obtener
     * @param callback Callback a ejecutar una vez completada la peticion
     */
    void getPersons(Gender gender, PersonListCallback callback);

    /**
     * Actualiza un listado de {@link Person}
     *
     * @param persons  Las personas a actualizar
     * @param callback Callback a ejecutar una vez completada la peticion
     */
    void savePersons(List<Person> persons, SavePersonsCallback callback);
}
