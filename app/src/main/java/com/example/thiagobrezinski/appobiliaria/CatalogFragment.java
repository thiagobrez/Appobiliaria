package com.example.thiagobrezinski.appobiliaria;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thiagobrezinski on 05/11/18.
 */

public class CatalogFragment extends Fragment {

    private RecyclerView recyclerView;
    private ImovelAdapter adapter;
    private List<Imovel> imoveis;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        View rootView = inflater.inflate(R.layout.fragment_catalog, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        imoveis = new ArrayList<>();
        adapter = new ImovelAdapter(getActivity(), imoveis);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new CatalogFragment.GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepararImoveis();

        return rootView;
    }

    /**
     * Adiciona imoveis para teste.
     */
    private void prepararImoveis() {
        int[] fotos = new int[] {
                R.drawable.imovel_1,
        };

        Imovel imovel = new Imovel(
                "Res. Tarumã",
                200000,
                "Rua José Bonifácio",
                3,
                "01/01/2020",
                12,
                fotos[0]
        );
        imoveis.add(imovel);

        imovel = new Imovel(
                "Res. João de Barro",
                500000,
                "Rua João de Barro",
                5,
                "05/05/2021",
                20,
                fotos[0]
        );
        imoveis.add(imovel);

        imovel = new Imovel(
                "Res. Mário Quintana",
                375000,
                "Rua Mario Quintana",
                4,
                "25/12/2019",
                24,
                fotos[0]
        );
        imoveis.add(imovel);

        imovel = new Imovel(
                "Casa de frente para o mar",
                450000,
                "Avenida Beira Mar",
                2,
                "01/01/2018",
                18,
                fotos[0]
        );
        imoveis.add(imovel);

        imovel = new Imovel(
                "Casa rústica",
                225000,
                "Avenida Campo Largo",
                2,
                "01/01/2018",
                18,
                fotos[0]
        );
        imoveis.add(imovel);

        adapter.notifyDataSetChanged();
    }


    /**
     * Converte dp para pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    /**
     * Seta os espaçamentos de cada item da grid.
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Retorna uma nova instância do fragmento.
     */
    public static CatalogFragment newInstance() {
        return new CatalogFragment();
    }
}