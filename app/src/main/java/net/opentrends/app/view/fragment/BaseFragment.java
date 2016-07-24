package net.opentrends.app.view.fragment;

import android.app.Fragment;
import android.os.Bundle;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        initPresenter();
    }

    abstract void initPresenter();
}
