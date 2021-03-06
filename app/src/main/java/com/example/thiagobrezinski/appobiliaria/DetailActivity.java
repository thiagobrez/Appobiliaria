package com.example.thiagobrezinski.appobiliaria;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

public class DetailActivity extends AppCompatActivity {

    private static final String DEVELOPER_KEY = "AIzaSyC3N1Ah7JAMAZiIhoxN7CXKUgYcYd1mjEQ";
    private static final String TAG = MainActivity.class.getSimpleName();
    private YouTubePlayerSupportFragment youTubePlayerFragment;
    private YouTubePlayer youTubePlayer;
    private Context context;
    private Imovel imovel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        context = this;
        this.imovel = (Imovel) getIntent().getSerializableExtra("imovel");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initCollapsingToolbar(this.imovel);

        ((TextView) findViewById(R.id.backdrop_title)).setText(imovel.getNome());
        ((TextView) findViewById(R.id.detail_valor)).setText("Preço: R$" + String.valueOf(imovel.getValor()));
        ((TextView) findViewById(R.id.detail_endereco)).setText("Endereço: " + imovel.getEndereco());
        ((TextView) findViewById(R.id.detail_numero_quartos)).setText("Número de quartos: " + String.valueOf(imovel.getNumeroQuartos()));
        ((TextView) findViewById(R.id.detail_data_entrega)).setText("Data de entrega: " + imovel.getDataEntrega());
        ((TextView) findViewById(R.id.detail_prazo_financiamento)).setText("Prazo de financiamento: " + String.valueOf(imovel.getPrazoFinanciamento()) + " meses");

        try {
            Glide.with(this).load(this.imovel.getFotoPath()).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }

        final Button mapButton = findViewById(R.id.map_button);
        mapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context, MapsActivity.class);
                intent.putExtra("imovel", imovel);
                startActivity(intent);
            }
        });

        initializeYoutubePlayer();
    }

    /**
     * Inicializa player do YouTube em um fragmento.
     */
    private void initializeYoutubePlayer() {

        youTubePlayerFragment = (YouTubePlayerSupportFragment) getSupportFragmentManager()
                .findFragmentById(R.id.youtube_player_fragment);

        if (youTubePlayerFragment == null)
            return;

        youTubePlayerFragment.initialize(DEVELOPER_KEY, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                                boolean wasRestored) {
                if (!wasRestored) {
                    youTubePlayer = player;
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                    youTubePlayer.cueVideo(imovel.getVideoURL());
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {
                Log.e(TAG, "Youtube Player View initialization failed");
            }
        });
    }

    /**
     * Inicializa a toolbar expandível
     */
    private void initCollapsingToolbar(Imovel imovel) {
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
                    collapsingToolbar.setTitle(imovel.getNome());
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }
}
