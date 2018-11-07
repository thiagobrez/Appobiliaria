package com.example.thiagobrezinski.appobiliaria;

/**
 * Created by thiagobrezinski on 07/11/18.
 */

public class Noticia {

    private String titulo;
    private String descricao;

    public Noticia(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
