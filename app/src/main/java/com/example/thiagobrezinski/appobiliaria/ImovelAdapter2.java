package com.example.thiagobrezinski.appobiliaria;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thiagobrezinski on 09/11/18.
 */

public class ImovelAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private RecyclerViewClickListener listener;
    private List<Imovel> imoveis = new ArrayList<>();

    public class RowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RecyclerViewClickListener mListener;
        private TextView
                nome,
                valor,
                endereco,
                numeroQuartos,
                dataEntrega,
                prazoFinanciamento;
        public ImageView foto;

        RowViewHolder(View view, RecyclerViewClickListener listener) {
            super(view);
            mListener = listener;
            nome = (TextView) view.findViewById(R.id.card_nome);
            valor = (TextView) view.findViewById(R.id.card_valor);
//            endereco = (TextView) view.findViewById(R.id.card_endereco);
//            numeroQuartos = (TextView) view.findViewById(R.id.card_numero_quartos);
//            dataEntrega = (TextView) view.findViewById(R.id.card_data_entrega);
//            prazoFinanciamento = (TextView) view.findViewById(R.id.card_prazo_financiamento);
            foto = (ImageView) view.findViewById(R.id.card_foto);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }
    }

    ImovelAdapter2(Context context, RecyclerViewClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void updateData(List<Imovel> dataset) {
        imoveis.clear();
        imoveis.addAll(dataset);
        notifyDataSetChanged();
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
            rowHolder.nome.setText(imovel.getNome());
            rowHolder.valor.setText("R$" + Integer.toString(imovel.getValor()));
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