package com.example.thiagobrezinski.appobiliaria;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;

/**
 * Created by thiagobrezinski on 31/10/18.
 */

public class ImovelAdapter extends RecyclerView.Adapter<ImovelAdapter.MyViewHolder> {
    private Context mContext;
    private List<Imovel> imoveis;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView
                nome,
                valor,
                endereco,
                numeroQuartos,
                dataEntrega,
                prazoFinanciamento;
        public ImageView foto;


        public MyViewHolder(View view) {
            super(view);
            nome = (TextView) view.findViewById(R.id.card_nome);
            valor = (TextView) view.findViewById(R.id.card_valor);
//            endereco = (TextView) view.findViewById(R.id.card_endereco);
//            numeroQuartos = (TextView) view.findViewById(R.id.card_numero_quartos);
//            dataEntrega = (TextView) view.findViewById(R.id.card_data_entrega);
//            prazoFinanciamento = (TextView) view.findViewById(R.id.card_prazo_financiamento);
            foto = (ImageView) view.findViewById(R.id.card_foto);
        }
    }

    public ImovelAdapter(Context mContext, List<Imovel> imoveis) {
        this.mContext = mContext;
        this.imoveis = imoveis;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.imovel_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Imovel imovel = imoveis.get(position);
        holder.nome.setText(imovel.getNome());
        holder.valor.setText("R$" + Integer.toString(imovel.getValor()));
//        holder.endereco.setText(imovel.getEndereco());
//        holder.numeroQuartos.setText(Integer.toString(imovel.getNumeroQuartos()));
//        holder.dataEntrega.setText(imovel.getDataEntrega());
//        holder.prazoFinanciamento.setText(Integer.toString(imovel.getPrazoFinanciamento()));
        Glide.with(mContext).load(imovel.getFotoPath()).into(holder.foto);
    }

    @Override
    public int getItemCount() {
        return imoveis.size();
    }
}
