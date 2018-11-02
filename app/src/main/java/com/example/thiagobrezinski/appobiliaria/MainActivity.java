package com.example.thiagobrezinski.appobiliaria;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        Imovel imovel1 = new Imovel(
                "Res. Tarumã",
                200000,
                "Rua José Bonifácio",
                3,
                "01/01/2020",
                12,
                "@drawable/imovel_1"
        );

        Imovel imovel2 = new Imovel(
                "Res. João de Barro",
                500000,
                "Rua João de Barro",
                5,
                "01/01/2020",
                12,
                "@drawable/imovel_1"
        );

        ArrayList<Imovel> imoveis = new ArrayList<>();
        imoveis.add(imovel1);
        imoveis.add(imovel2);

        mAdapter = new CardAdapter(imoveis);
        mRecyclerView.setAdapter(mAdapter);
    }

}
