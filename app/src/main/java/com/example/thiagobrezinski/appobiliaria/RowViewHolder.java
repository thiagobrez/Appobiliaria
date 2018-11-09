package com.example.thiagobrezinski.appobiliaria;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by thiagobrezinski on 09/11/18.
 */

public class RowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private RecyclerViewClickListener listener;
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
        this.listener = listener;
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
        listener.onClick(view, getAdapterPosition());
    }

    public RecyclerViewClickListener getListener() {
        return listener;
    }

    public void setListener(RecyclerViewClickListener listener) {
        this.listener = listener;
    }

    public TextView getNome() {
        return nome;
    }

    public void setNome(TextView nome) {
        this.nome = nome;
    }

    public TextView getValor() {
        return valor;
    }

    public void setValor(TextView valor) {
        this.valor = valor;
    }

    public TextView getEndereco() {
        return endereco;
    }

    public void setEndereco(TextView endereco) {
        this.endereco = endereco;
    }

    public TextView getNumeroQuartos() {
        return numeroQuartos;
    }

    public void setNumeroQuartos(TextView numeroQuartos) {
        this.numeroQuartos = numeroQuartos;
    }

    public TextView getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(TextView dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public TextView getPrazoFinanciamento() {
        return prazoFinanciamento;
    }

    public void setPrazoFinanciamento(TextView prazoFinanciamento) {
        this.prazoFinanciamento = prazoFinanciamento;
    }

    public ImageView getFoto() {
        return foto;
    }

    public void setFoto(ImageView foto) {
        this.foto = foto;
    }
}
