package com.example.thiagobrezinski.appobiliaria;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import org.json.JSONArray;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navigationView;
    private JSONArray jsonImoveis;
    private JSONArray jsonNoticias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:3000";

        JsonArrayRequest imoveisJson = new JsonArrayRequest
                (Request.Method.GET, url + "/imoveis", null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        jsonImoveis = response;
                        navigationView.setSelectedItemId(R.id.navigation_catalog);
                        Fragment catalogFragment = CatalogFragment.newInstance();
                        openFragment(catalogFragment);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                    }
                });

        JsonArrayRequest noticiasJson = new JsonArrayRequest
                (Request.Method.GET, url + "/noticias", null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        jsonNoticias = response;
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        queue.add(imoveisJson);
        queue.add(noticiasJson);

        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initCollapsingToolbar();

        try {
            Glide.with(this).load(R.drawable.appobiliaria).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Inicializa a toolbar expandível
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    /**
     * Acionado quando um botão da bottom navigation bar é pressionado.
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        TextView backdropSubtitle = (TextView) findViewById(R.id.backdrop_subtitle);

        switch (item.getItemId()) {
            case R.id.navigation_news: {
                backdropSubtitle.setText(R.string.title_news);
                Fragment newsFragment = NewsFragment.newInstance();
                openFragment(newsFragment);
                break;
            }
            case R.id.navigation_catalog: {
                backdropSubtitle.setText(R.string.title_catalog);
                Fragment catalogFragment = CatalogFragment.newInstance();
                openFragment(catalogFragment);
                break;
            }
        }
        return true;
    }

    /**
     * Substitui o fragmento sendo exibido no FrameLayout.
     */
    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public JSONArray getJsonImoveis() {
        return jsonImoveis;
    }

    public JSONArray getJsonNoticias() {
        return jsonNoticias;
    }
}