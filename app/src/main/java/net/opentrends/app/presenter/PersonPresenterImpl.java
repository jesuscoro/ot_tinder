package net.opentrends.app.presenter;

import net.opentrends.app.view.PersonsView;
import net.opentrends.domain.model.Gender;
import net.opentrends.domain.model.Person;
import net.opentrends.domain.repository.PersonRepository;
import net.opentrends.domain.usecase.GetPersonsUseCase;
import net.opentrends.domain.usecase.GetPersonsUseCaseImpl;
import net.opentrends.domain.usecase.UpdatePersonUseCase;
import net.opentrends.domain.usecase.UpdatePersorUseCaseImpl;
import net.opentrends.model.repository.PersonRepositoryImpl;

import java.util.List;

/**
 * Implementacion del presenter de personas. Hace de intermediario entre la vista y los casos de uso.
 */
public class PersonPresenterImpl implements PersonPresenter {

    private PersonsView personsView;
    private GetPersonsUseCase getPersonsUseCase;
    private UpdatePersonUseCase updatePersonUseCase;

    GetPersonsUseCase.Callback getPersonsCallback = new GetPersonsUseCase.Callback() {
        @Override
        public void onGetPersons(List<Person> persons) {
            personsView.addPersons(persons);
            personsView.hideProgress();
            personsView.hideSelectGender();
        }

        @Override
        public void onError(String message) {
            personsView.hideProgress();
            personsView.showMessage(message);
        }
    };

    UpdatePersonUseCase.Callback updatePersonCallback = new UpdatePersonUseCase.Callback() {
        @Override
        public void onUpdatePerson() {
            personsView.notifyPersonChanged();
        }

        @Override
        public void onUpdatePersonError(String message) {
            personsView.showMessage(message);
        }
    };

    public PersonPresenterImpl(PersonsView personsView) {
        PersonRepository repository = new PersonRepositoryImpl();

        this.personsView = personsView;
        this.getPersonsUseCase = new GetPersonsUseCaseImpl(repository);
        this.updatePersonUseCase = new UpdatePersorUseCaseImpl(repository);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void changeFavourite(Person person) {
        person.setFavourite(!person.getFavourite());
        this.updatePersonUseCase.updatePerson(person, updatePersonCallback);
    }

    @Override
    public void getPersons(Gender gender) {
        this.personsView.showProgress();
        this.getPersonsUseCase.getPersons(gender, getPersonsCallback);
    }
}
