package net.opentrends.model.restclient;

import net.opentrends.domain.model.Gender;
import net.opentrends.domain.model.Person;
import net.opentrends.model.restclient.model.Result;
import net.opentrends.model.restclient.model.ServerResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Implementacion del cliente rest de personas. Realiza las llamadas remotas a traves de una
 * interface que sera instanciada por la libreria retrofit en la que se delegan las peticiones
 * <p/>
 * Expone un metodo para obtener personas del servicio web. Aunque este metodo recibe personas
 * de ambos sexos, antes de llamar al callback que lo invoco se filtran por un sexo determinado
 *
 * @see net.opentrends.model.repository.datasource.RemotePersonDataStore
 * @see "http://square.github.io/retrofit/"
 */
public class PersonRestClientImpl extends RestClient implements PersonRestClient {

    private static final String METHOD = "/api";
    private static final String PARAM_KEY = "key";
    private static final String KEY_VALUE = "LMW0-SW97-ISC4-FF25";

    private static final String PARAM_ID = "id";
    private static final String ID_VALUE = "t60ldyb";

    private static final String PARAM_RESULTS = "results";
    private static final int RESULTS_VALUE = 20;

    private static final int PAYMENT_REQUIRED = 402;

    @Override
    public void getRemotePersons(final Gender gender, final GetRemotePersonsCallback callback) {
        RandomApi randomApi = mRestAdapter.create(RandomApi.class);
        randomApi.getPersons(KEY_VALUE, ID_VALUE, RESULTS_VALUE, new Callback<ServerResponse>() {
            @Override
            public void success(ServerResponse serverResponse, Response response) {
                final List<Person> persons = new ArrayList<>();
                List<Result> results = serverResponse.getResults();

                for (Result r : results) {
                    Person person = r.getEntity();
                    if (person.getGender().equals(gender.toString())) {
                        persons.add(person);
                    }
                }

                callback.onGetRemotePersons(persons);
            }

            @Override
            public void failure(RetrofitError error) {
//                if (error != null && error.getResponse() != null && error.getResponse().getStatus() == PAYMENT_REQUIRED) {
////                Mock de personas por si se alcanza el limite de peticiones poder seguir haciendo pruebas. Eliminar al gusto
//                    List<Person> fakePersons = new ArrayList<>();
//                    fakePersons.add(new Person(0, "male", "1", "", "http://api.randomuser.me/portraits/men/1.jpg", "", false));
//                    fakePersons.add(new Person(0, "female", "2", "", "http://api.randomuser.me/portraits/women/1.jpg", "", false));
//                    fakePersons.add(new Person(0, "male", "3", "", "http://api.randomuser.me/portraits/men/2.jpg", "", false));
//                    fakePersons.add(new Person(0, "female", "4", "", "http://api.randomuser.me/portraits/women/2.jpg", "", false));
//                    fakePersons.add(new Person(0, "male", "5", "", "http://api.randomuser.me/portraits/men/3.jpg", "", false));
//                    fakePersons.add(new Person(0, "female", "6", "", "http://api.randomuser.me/portraits/women/3.jpg", "", false));
//                    fakePersons.add(new Person(0, "male", "7", "", "http://api.randomuser.me/portraits/men/4.jpg", "", false));
//                    fakePersons.add(new Person(0, "female", "8", "", "http://api.randomuser.me/portraits/women/4.jpg", "", false));
//                    fakePersons.add(new Person(0, "male", "9", "", "http://api.randomuser.me/portraits/men/5.jpg", "", false));
//                    fakePersons.add(new Person(0, "female", "10", "", "http://api.randomuser.me/portraits/women/5.jpg", "", false));
//                    fakePersons.add(new Person(0, "male", "11", "", "http://api.randomuser.me/portraits/men/6.jpg", "", false));
//                    fakePersons.add(new Person(0, "female", "12", "", "http://api.randomuser.me/portraits/women/6.jpg", "", false));
//                    fakePersons.add(new Person(0, "male", "13", "", "http://api.randomuser.me/portraits/men/7.jpg", "", false));
//                    fakePersons.add(new Person(0, "female", "14", "", "http://api.randomuser.me/portraits/women/7.jpg", "", false));
//                    fakePersons.add(new Person(0, "male", "15", "", "http://api.randomuser.me/portraits/men/8.jpg", "", false));
//                    fakePersons.add(new Person(0, "female", "16", "", "http://api.randomuser.me/portraits/women/8.jpg", "", false));
//                    fakePersons.add(new Person(0, "male", "17", "", "http://api.randomuser.me/portraits/men/9.jpg", "", false));
//                    fakePersons.add(new Person(0, "female", "18", "", "http://api.randomuser.me/portraits/women/9.jpg", "", false));
//                    fakePersons.add(new Person(0, "male", "19", "", "http://api.randomuser.me/portraits/men/10.jpg", "", false));
//                    fakePersons.add(new Person(0, "female", "20", "", "http://api.randomuser.me/portraits/women/10.jpg", "", false));
//                    fakePersons.add(new Person(0, "male", "21", "", "http://api.randomuser.me/portraits/men/11.jpg", "", false));
//                    fakePersons.add(new Person(0, "female", "22", "", "http://api.randomuser.me/portraits/women/11.jpg", "", false));
//                    fakePersons.add(new Person(0, "male", "23", "", "http://api.randomuser.me/portraits/men/12.jpg", "", false));
//                    fakePersons.add(new Person(0, "female", "24", "", "http://api.randomuser.me/portraits/women/12.jpg", "", false));
//
//                    List<Person> persons = new ArrayList<Person>();
//                    for (Person p : fakePersons) {
//                        if (p.getGender().equals(gender.toString())) {
//                            persons.add(p);
//                        }
//                    }
//
//                    callback.onGetRemotePersons(persons);
//                    return;
//                }

                callback.onGetRemotePersonsError(error.getMessage());
            }
        });
    }

    /**
     * Interface para retrofit del servicio rest remoto.
     */
    interface RandomApi {

        /**
         * Metodo remoto para obtener el listado de personas
         *
         * @param key      parametro Key de la peticion
         * @param id       parametro id de la peticion
         * @param results  parametro results de la peticion. El numero maximo de elementos a obtener
         * @param callback Callback de retrofit para procesar el resultado
         */
        @GET(METHOD)
        void getPersons(@Query(PARAM_KEY) String key, @Query(PARAM_ID) String id, @Query(PARAM_RESULTS) int results, Callback<ServerResponse> callback);
    }
}
