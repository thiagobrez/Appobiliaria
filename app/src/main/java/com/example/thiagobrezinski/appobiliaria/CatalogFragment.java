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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
        adapter = new ImovelAdapter(getContext(), imoveis);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new CatalogFragment.GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        try {
            prepararImoveis(((MainActivity) getActivity()).getJsonImoveis());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rootView;
    }

    /**
     * Formata os imoveis recebidos do servidor e adiciona na lista.
     */
    private void prepararImoveis(JSONArray jsonImoveis) throws JSONException {
        int[] fotos = new int[] {
                R.drawable.imovel_1,
//                R.drawable.imovel_2,
//                R.drawable.imovel_3,
//                R.drawable.imovel_4,
//                R.drawable.imovel_5,
//                R.drawable.imovel_6,
        };

        for (int i = 0; i < jsonImoveis.length(); i++) {
            JSONObject json = jsonImoveis.getJSONObject(i);
            this.imoveis.add(new Imovel(
               json.getInt("id"),
               json.getString("nome"),
               json.getInt("valor"),
               json.getString("endereco"),
               json.getInt("numeroQuartos"),
               json.getString("dataEntrega"),
               json.getInt("prazoFinanciamento"),
               fotos[0]
            ));
        }

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