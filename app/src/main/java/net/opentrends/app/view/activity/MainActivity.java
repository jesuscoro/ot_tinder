package net.opentrends.app.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import net.opentrends.app.R;
import net.opentrends.app.view.fragment.PersonsFragment;

/**
 * Activity unica de la aplicacion. Solamente tiene un fragment.
 *
 * @see PersonsFragment
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction().add(R.id.frameLayout_content, PersonsFragment.newInstance()).commit();
    }
}
