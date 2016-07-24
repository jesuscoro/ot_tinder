package net.opentrends.app.view.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import net.opentrends.app.R;
import net.opentrends.domain.model.Gender;
import net.opentrends.domain.model.Person;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Adaptador de personas.
 *
 * @see "https://github.com/bumptech/glide"
 * @see "http://jakewharton.github.io/butterknife/"
 */
public class PersonsAdapter extends RecyclerView.Adapter<PersonsAdapter.PersonViewHolder> {

    private List<Person> items;

    private Context context;

    private OnPersonInteractionListener mListener;

    public PersonsAdapter(Context context, List<Person> items, OnPersonInteractionListener listener) {
        mListener = listener;
        this.context = context;

        this.items = items;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_person, viewGroup, false);
        return new PersonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder viewHolder, int position) {
        Person person = items.get(position);
        viewHolder.bindPerson(person);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItems(List<Person> persons) {
        items.addAll(persons);
        notifyDataSetChanged();
    }

    public void refresh() {
        notifyDataSetChanged();
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {

        private Person person;

        @BindView(R.id.item_person_imageView_person)
        ImageView imageViewPerson;
        @BindView(R.id.item_person_imageView_favourite)
        ImageView imageViewFavourite;

        public PersonViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void bindPerson(final Person person) {
            this.person = person;

            int placeholder = person.getGender().equals(Gender.MALE.toString()) ? R.drawable.generic_male : R.drawable.generic_female;
            Glide.with(context).load(person.getPicture()).placeholder(placeholder).into(imageViewPerson);

            if (person.getFavourite()) {
                imageViewFavourite.setImageResource(R.drawable.ic_favourite);
            } else {
                imageViewFavourite.setImageResource(R.drawable.ic_no_favourite);
            }
        }

        @OnClick(R.id.item_person_imageView_favourite)
        public void onClick() {
            if (mListener != null) {
                mListener.onFavouriteClick(person);
            }
        }
    }

    /**
     * Interface para interactuar con los elementos de la lista.
     */
    public interface OnPersonInteractionListener {

        /**
         * Click en el boton de favorito de una {@link Person}
         *
         * @param person La persona que se quiere modificar el estado de favorito
         */
        void onFavouriteClick(Person person);
    }
}