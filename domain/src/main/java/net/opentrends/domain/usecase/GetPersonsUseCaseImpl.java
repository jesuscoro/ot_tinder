package net.opentrends.domain.usecase;


import net.opentrends.domain.model.Gender;
import net.opentrends.domain.model.Person;
import net.opentrends.domain.repository.PersonRepository;

import java.util.List;

/**
 * {@inheritDoc}
 * <p/>
 * Implementacion del caso de uso para obtener el listado de personas. Llamara a la capa
 * {@link PersonRepository} para obtener el listado de personas. Una vez completada la peticion, e
 * implementando {@link PersonRepository.GetPersonsCallback}
 * devolvera el listado de personas o el mensaje de error
 *
 * @see PersonRepository
 * @see PersonRepository.GetPersonsCallback
 */
public class GetPersonsUseCaseImpl implements GetPersonsUseCase {

    /**
     * Repositorio para obtener el listado de {@link Person}
     */
    PersonRepository repository;

    /**
     * Constructor unico parametrizado con un objeto {@link PersonRepository}
     *
     * @param repository El repositorio para obtener el listado de {@link Person}
     */
    public GetPersonsUseCaseImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getPersons(Gender gender, final Callback callback) {
        repository.getPersons(gender, new PersonRepository.GetPersonsCallback() {
            @Override
            public void onGetPersons(List<Person> persons) {
                callback.onGetPersons(persons);
            }

            @Override
            public void onError(String message) {
                callback.onError(message);
            }
        });

    }
}
