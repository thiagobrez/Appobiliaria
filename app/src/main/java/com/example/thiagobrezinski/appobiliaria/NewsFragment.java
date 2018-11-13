package com.example.thiagobrezinski.appobiliaria;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thiagobrezinski on 05/11/18.
 */

public class NewsFragment extends Fragment {

    private RecyclerView recyclerView;
    private NoticiaAdapter adapter;
    private List<Noticia> noticias;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        noticias = new ArrayList<>();
        adapter = new NoticiaAdapter(getActivity(), noticias);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        try {
            prepararNoticias(((MainActivity) getActivity()).getJsonNoticias());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rootView;
    }

    /**
     * Formata as noticias recebidas do servidor e adiciona na lista.
     */
    private void prepararNoticias(JSONArray jsonNoticias) throws JSONException {
        for (int i = 0; i < jsonNoticias.length(); i++) {
            JSONObject json = jsonNoticias.getJSONObject(i);
            this.noticias.add(new Noticia(
                    json.getInt("id"),
                    json.getString("titulo"),
                    json.getString("descricao")
            ));
        }

        adapter.notifyDataSetChanged();
    }

    /**
     * Retorna uma nova instância do fragmento.
     */
    public static NewsFragment newInstance() {
        return new NewsFragment();
    }
}