package com.example.thiagobrezinski.appobiliaria;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by thiagobrezinski on 31/10/18.
 */

public class NoticiaAdapter extends RecyclerView.Adapter<NoticiaAdapter.MyViewHolder> {
    private Context mContext;
    private List<Noticia> noticias;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView titulo, descricao;

        public MyViewHolder(View view) {
            super(view);
            titulo = (TextView) view.findViewById(R.id.noticia_titulo);
            descricao = (TextView) view.findViewById(R.id.noticia_descricao);
        }
    }

    public NoticiaAdapter(Context mContext, List<Noticia> noticias) {
        this.mContext = mContext;
        this.noticias = noticias;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.noticia_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Noticia noticia = noticias.get(position);
        holder.titulo.setText(noticia.getTitulo());
        holder.descricao.setText(noticia.getDescricao());
    }

    @Override
    public int getItemCount() {
        return noticias.size();
    }
}
