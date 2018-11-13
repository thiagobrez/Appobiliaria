package com.example.thiagobrezinski.appobiliaria;

import java.io.Serializable;

/**
 * Created by thiagobrezinski on 31/10/18.
 */

public class Imovel implements Serializable {

    private int id;
    private String nome;
    private int valor;
    private String endereco;
    private int numeroQuartos;
    private String dataEntrega;
    private int prazoFinanciamento;
    private int fotoPath;
    private String lat;
    private String lng;
    private String videoURL;

    public Imovel(int id,
                  String nome,
                  int valor,
                  String endereco,
                  int numeroQuartos,
                  String dataEntrega,
                  int prazoFinanciamento,
                  int fotoPath,
                  String lat,
                  String lng,
                  String videoURL
    ) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.endereco = endereco;
        this.numeroQuartos = numeroQuartos;
        this.dataEntrega = dataEntrega;
        this.prazoFinanciamento = prazoFinanciamento;
        this.fotoPath = fotoPath;
        this.lat = lat;
        this.lng = lng;
        this.videoURL = videoURL;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getNumeroQuartos() {
        return numeroQuartos;
    }

    public void setNumeroQuartos(int numeroQuartos) {
        this.numeroQuartos = numeroQuartos;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public int getPrazoFinanciamento() {
        return prazoFinanciamento;
    }

    public void setPrazoFinanciamento(int prazoFinanciamento) {
        this.prazoFinanciamento = prazoFinanciamento;
    }

    public int getFotoPath() {
        return fotoPath;
    }

    public void setFotoPath(int fotoPath) {
        this.fotoPath = fotoPath;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }
}
