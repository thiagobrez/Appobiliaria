package com.example.thiagobrezinski.appobiliaria;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import java.util.List;

/**
 * Created by thiagobrezinski on 09/11/18.
 */

public class ImovelAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private RecyclerViewClickListener listener;
    private List<Imovel> imoveis;

    ImovelAdapter2(Context context, RecyclerViewClickListener listener, List<Imovel> imoveis) {
        this.context = context;
        this.listener = listener;
        this.imoveis = imoveis;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.imovel_card, parent, false);
        return new RowViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RowViewHolder) {
            RowViewHolder rowHolder = (RowViewHolder) holder;

            final Imovel imovel = imoveis.get(position);
            rowHolder.getNome().setText(imovel.getNome());
            rowHolder.getValor().setText("R$" + Integer.toString(imovel.getValor()));
//        rowHolder.endereco.setText(imovel.getEndereco());
//        rowHolder.numeroQuartos.setText(Integer.toString(imovel.getNumeroQuartos()));
//        rowHolder.dataEntrega.setText(imovel.getDataEntrega());
//        rowHolder.prazoFinanciamento.setText(Integer.toString(imovel.getPrazoFinanciamento()));
            Glide.with(context).load(imovel.getFotoPath()).into(rowHolder.foto);
        }
    }

    @Override
    public int getItemCount() {
        return imoveis.size();
    }
}