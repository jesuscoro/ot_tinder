package net.opentrends.domain.usecase;


import net.opentrends.domain.model.Person;
import net.opentrends.domain.repository.PersonRepository;

/**
 * {@inheritDoc}
 * <p/>
 * Implementacion del caso de uso para actualizar un objeto {@link Person}. Llamara a la capa
 * {@link PersonRepository} para actualizarlo. Una vez completada la peticion, e
 * implementando {@link PersonRepository.UpdatePersonCallback} devolvera el mensaje de error o
 * ejecutara el metodo de respuesta correcta
 *
 * @see PersonRepository
 * @see PersonRepository.GetPersonsCallback
 */
public class UpdatePersorUseCaseImpl implements UpdatePersonUseCase {


    /**
     * Repositorio para actualizar una persona
     */
    PersonRepository repository;

    /**
     * Constructor unico parametrizado con un objeto {@link PersonRepository}
     *
     * @param repository El repositorio para actualizar el {@link Person}
     */
    public UpdatePersorUseCaseImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public void updatePerson(Person person, final Callback callback) {
        repository.updatePerson(person, new PersonRepository.UpdatePersonCallback() {
            @Override
            public void onUpdatePerson() {
                callback.onUpdatePerson();
            }

            @Override
            public void onUpdatePersonError(String message) {
                callback.onUpdatePersonError(message);
            }
        });


    }
}
