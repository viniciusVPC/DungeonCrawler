package com.example.items;

public class Arma {
    int areaDano;
    float multiplicador;
    String nome, descricao;

    public Arma(int areaDano, float multiplicador, String nome, String descricao) {
        this.areaDano = areaDano;
        this.multiplicador = multiplicador;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getAreaDano() {
        return areaDano;
    }

    public float getMultiplicador() {
        return multiplicador;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
