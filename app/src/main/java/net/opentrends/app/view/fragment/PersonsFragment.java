package net.opentrends.app.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.opentrends.app.R;
import net.opentrends.app.presenter.PersonPresenter;
import net.opentrends.app.presenter.PersonPresenterImpl;
import net.opentrends.app.view.PersonsView;
import net.opentrends.app.view.adapter.PersonsAdapter;
import net.opentrends.domain.model.Gender;
import net.opentrends.domain.model.Person;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Fragment unico de la aplicacion. Tiene una capa para seleccionar el {@link Gender} de {@link Person}
 * a mostrar y otra para mostrarlas. En esta ultima, en cada persona, se puede modificar su estado
 * de favorito.
 * <p/>
 * Implementa {@link PersonsView} y contiene una instancia de {@link PersonPresenter}, que sera
 * la encargada de realizar las llamadas a los casos de uso
 *
 * @see "http://jakewharton.github.io/butterknife/"
 */
public class PersonsFragment extends BaseFragment implements PersonsView,
        PersonsAdapter.OnPersonInteractionListener/*, View.OnClickListener*/ {

    private static final int COLUMNS = 2;

    @BindView(R.id.fragment_persons_relativeLayout_loading)
    View viewLoading;

    @BindView(R.id.fragment_persons_relativeLayout_select)
    View viewSelect;

    @BindView(R.id.fragment_persons_recyclerView_items)
    RecyclerView recyclerViewItems;

    private PersonPresenter presenter;
    private PersonsAdapter adapter;

    public PersonsFragment() {
        // Required empty public constructor
    }

    public static PersonsFragment newInstance() {
        PersonsFragment fragment = new PersonsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    // ==========================================================
    // Fragment
    // ==========================================================
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_persons, container, false);

        ButterKnife.bind(this, rootView);

        adapter = new PersonsAdapter(getActivity(), new ArrayList<Person>(), this);
        recyclerViewItems.setAdapter(adapter);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), PersonsFragment.COLUMNS);
        recyclerViewItems.setLayoutManager(layoutManager);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.presenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.presenter.pause();
    }

    // ==========================================================
    // BaseFragment
    // ==========================================================
    @Override
    void initPresenter() {
        this.presenter = new PersonPresenterImpl(this);
    }

    // ==========================================================
    // PersonListView
    // ==========================================================
    @Override
    public void showProgress() {
        viewLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        viewLoading.setVisibility(View.GONE);
    }

    @Override
    public void addPersons(List<Person> persons) {
        adapter.addItems(persons);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void notifyPersonChanged() {
        adapter.refresh();
    }

    @Override
    public void hideSelectGender() {
        viewSelect.setVisibility(View.GONE);
    }

    // ==========================================================
    // PersonsAdapter.OnPersonInteractionListener
    // ==========================================================
    @Override
    public void onFavouriteClick(Person person) {
        presenter.changeFavourite(person);
    }

    // ==========================================================
    // View.OnClickListener
    // ==========================================================
    @OnClick({R.id.fragment_persons_button_female, R.id.fragment_persons_button_male, R.id.fragment_persons_relativeLayout_loading})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_persons_button_female:
                presenter.getPersons(Gender.FEMALE);
                break;
            case R.id.fragment_persons_button_male:
                presenter.getPersons(Gender.MALE);
                break;
        }
    }
}
