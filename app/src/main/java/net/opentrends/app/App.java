package net.opentrends.app;

import com.orm.SugarApp;
import com.orm.SugarContext;

/**
 * Clase App de la aplicacion. Extendiende de SugarApp para inicializar la base de datos
 *
 * @see "http://satyan.github.io/sugar/"
 */
public class App extends SugarApp {

    @Override
    public void onCreate() {
        super.onCreate();

        SugarContext.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
