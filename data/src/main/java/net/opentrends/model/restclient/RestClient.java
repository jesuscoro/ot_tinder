package net.opentrends.model.restclient;

import com.squareup.okhttp.OkHttpClient;

import net.opentrends.model.BuildConfig;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Clase base para los servicios remotos. Inicializa el adaptador de retrofit para instanciar
 * las interfaces remotas. Ademas, se establece el nivel de log y los timeouts de las peticiones
 *
 * @see "http://square.github.io/retrofit/"
 */
public class RestClient {

    private static final int READ_TIMEOUT = 60 * 1000;
    private static final int CONNECT_TIMEOUT = 5 * 1000;

    protected retrofit.RestAdapter mRestAdapter;

    protected RestClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.setConnectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);

        retrofit.RestAdapter.Builder builder = new retrofit.RestAdapter.Builder();

        builder.setLogLevel(RestAdapter.LogLevel.BASIC)
                .setEndpoint(BuildConfig.END_POINT)
                .setClient(new OkClient(okHttpClient));

        mRestAdapter = builder.build();
    }
}
