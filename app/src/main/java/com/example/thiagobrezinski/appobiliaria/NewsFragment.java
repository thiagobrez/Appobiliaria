package com.example.thiagobrezinski.appobiliaria;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thiagobrezinski on 05/11/18.
 */

public class NewsFragment extends Fragment {

    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private List<Imovel> imoveis;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {

        View view = inflater.inflate(
                R.layout.fragment_news,
                container,
                false
        );

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        imoveis = new ArrayList<>();
        adapter = new CardAdapter(this, imoveis);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new MainActivity.GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepararImoveis();

        try {
            Glide.with(this).load(R.drawable.imovel_1).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }

//        return inflater.inflate(
//                R.layout.fragment_news,
//                container,
//                false
//        );
    }

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }
}