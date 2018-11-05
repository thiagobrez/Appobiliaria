package com.example.thiagobrezinski.appobiliaria;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by thiagobrezinski on 05/11/18.
 */

public class CatalogFragment extends Fragment {
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(
                R.layout.fragment_catalog,
                container,
                false
        );
    }

    public static CatalogFragment newInstance() {
        return new CatalogFragment();
    }
}