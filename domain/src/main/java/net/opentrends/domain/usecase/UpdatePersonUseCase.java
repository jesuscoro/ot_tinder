package net.opentrends.domain.usecase;


import net.opentrends.domain.model.Person;

/**
 * Caso de uso para actualizar un {@link Person}
 */
public interface UpdatePersonUseCase {

    /**
     * Actualiza un {@link Person}
     *
     * @param person   La persona a actualizar
     * @param callback Callback a ejecutar al completarse la peticion
     */
    void updatePerson(Person person, Callback callback);

    /**
     * Interface con los metodos a ejecutar al completarse la peticion de actualizacion de
     * {@link Person}
     * <p/>
     * Expone metodos a ejecutar si la peticion se completo correctamente o para devolver el mensaje
     * con el error en caso contrario
     */
    interface Callback {

        /**
         * Metodo a ejecutar si la peticion se completo correctamente
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
