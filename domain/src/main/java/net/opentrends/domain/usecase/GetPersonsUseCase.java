package net.opentrends.domain.usecase;


import net.opentrends.domain.model.Gender;
import net.opentrends.domain.model.Person;

import java.util.List;

/**
 * Caso de uso para obtener el listado de {@link Person}
 */
public interface GetPersonsUseCase {

    /**
     * Obtiene un listado de {@link Person} de un {@link Gender} determinado.
     *
     * @param gender   El sexo de las peraonas a obtener
     * @param callback Callback a ejecutar al completarse la peticion
     */
    void getPersons(Gender gender, Callback callback);

    /**
     * Interface con los metodos a ejecutar al completarse la peticion del listado de {@link Person}.
     * <p/>
     * Expone metodos para devolver el listado si la peticion se completo correctamente o para
     * devolver el mensaje con el error en caso contrario
     */
    interface Callback {

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
}
